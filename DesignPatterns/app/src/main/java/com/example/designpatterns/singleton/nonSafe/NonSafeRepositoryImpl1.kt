package com.example.designpatterns.singleton.nonSafe


import kotlinx.serialization.json.Json
import java.io.File

class NonSafeRepositoryImpl1 private constructor() {

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

    // минусы реализации - !! небезопасный вызов instance
    companion object {
        private var instance: NonSafeRepositoryImpl1? = null

        fun getInstance(password: String): NonSafeRepositoryImpl1 {
            if (password == "1") {
                if (instance == null) instance = NonSafeRepositoryImpl1()
                return instance!!
            } else throw Exception("Invalid passward $password")
        }
    }
}

fun main() {
    NonSafeRepositoryImpl1.getInstance("1").persons.forEach(::println)
}

