package observer

import com.example.designpatterns.singleton.nonSafe.Person
import kotlinx.serialization.json.Json
import java.io.File

class SafeDoubleCheckRepository private constructor() {

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

    fun addPerson(firstName: String, lastName: String, age: Int) {
        val ids = _persons.map { it.id }
        println("First name")
        val person = Person(
            id = ids.max() + 1,
            firstName = firstName,
            lastName = lastName,
            age = age
        )
        _persons.add(person)
    }

    fun deletePerson(id: Int) {
        _persons.removeIf({ it.id == id })
    }

    fun saveChanges() {
        val content = Json.encodeToString(_persons)
        file.writeText(content)
    }

    companion object {
        private var instance: SafeDoubleCheckRepository? = null
        private var lock = Any()

        fun getInstance(password: String): SafeDoubleCheckRepository {
            if (password == "1") {

                instance?.let {
                    return it
                }

                synchronized(lock) {
                    instance?.let {
                        return it
                    }
                    return SafeDoubleCheckRepository()
                        .also { instance = it }
                }
            } else throw Exception("Invalid passward $password")
        }
    }
}

