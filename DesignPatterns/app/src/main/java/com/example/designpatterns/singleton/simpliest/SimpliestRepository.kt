package com.example.designpatterns.singleton.simpliest


import com.example.designpatterns.singleton.nonSafe.Person
import kotlinx.serialization.json.Json
import java.io.File

object SimpliestRepository {

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
}

fun main(){
    println(SimpliestRepository.persons)
}

