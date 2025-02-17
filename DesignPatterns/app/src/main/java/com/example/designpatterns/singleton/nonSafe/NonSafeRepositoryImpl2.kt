package com.example.designpatterns.singleton.nonSafe


import kotlinx.serialization.json.Json
import java.io.File

class NonSafeRepositoryImpl2 private constructor() {

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
    // + при создани ProtectedPersonRepositoryImpl2 мы читаем данные из файла, что может быть долго,
    // но lateinit создает объект в момент первого образения к нему, так что это будет в getInstance
    // и мы сможем делать это в другом потоке, поэтому основной не заблокируется

    // lateinit как правило используется при DI
    companion object {
        private lateinit var myInstance: NonSafeRepositoryImpl2

        fun getInstance(password: String): NonSafeRepositoryImpl2 {
            if (password == "1") {
                // ссылка на свойство instance
                if (Companion::myInstance.isInitialized)
                    myInstance = NonSafeRepositoryImpl2()
                return myInstance
            } else throw Exception("Invalid passward $password")
        }
    }
}

fun main() {
    NonSafeRepositoryImpl2.getInstance("1").persons.forEach(::println)
}

