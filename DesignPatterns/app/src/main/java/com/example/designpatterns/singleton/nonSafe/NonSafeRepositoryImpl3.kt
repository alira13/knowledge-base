package com.example.designpatterns.singleton.nonSafe


import kotlinx.serialization.json.Json
import java.io.File

class NonSafeRepositoryImpl3 private constructor() {

    private val file = File("Persons.json")

    private val _persons: MutableList<Person> = loadPersons()

    val persons: List<Person>
        get() {
            return _persons.toList()
        }

    private fun loadPersons(): MutableList<Person> {
        val fromFile = file.readText()
        return Json.decodeFromString<MutableList<Person>>(fromFile)
    }

    // внутри companion object код относится не к экземпляру класса, а именно к самому классу,
    // даже если не будет создано ни одного объекта класса, мы все равно сможем обратиться
    // к свойствам и методам внутри companion object через название класса

    // минусы реализации - lateinit, мы сами гарантируем, что не null
    companion object {
        // если ProtectedPersonRepositoryImpl3 с конструктором, то не получится
        // он вызовется до getInstance, куда мы прокидываем параметр
        private val myInstance: NonSafeRepositoryImpl3 = NonSafeRepositoryImpl3()

        fun getInstance(password: String): NonSafeRepositoryImpl3 {
            if (password == "1") {
                return myInstance
            } else throw Exception("Invalid passward $password")
        }
    }
}

fun main() {
    NonSafeRepositoryImpl3.getInstance("1").persons.forEach(::println)
}
