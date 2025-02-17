package com.example.designpatterns.singleton.safeDoubleCheck


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

    // внутри companion object код относится не к экземпляру класса, а именно к самому классу,
    // даже если не будет создано ни одного объекта класса, мы все равно сможем обратиться
    // к свойствам и методам внутри companion object через название класса

    // минусы реализации - !! небезопасный вызов instance


    // Double check реализация
    companion object {
        private var instance: SafeDoubleCheckRepository? = null

        // замок для синхронизации для решения проблемы гонки потоков
        private var lock = Any()

        fun getInstance(password: String): SafeDoubleCheckRepository {
            if (password == "1") {
                // FIRST CHECK
                // чтобы параллельные потоки не ждали, если instance уже создан
                // они же его не создают, а просто получают уже. Ничего опасного
                // Заменили if (instance != null) return instance!! на безопасный вызов+let
                instance?.let {
                    return it
                }
                // SECOND CHECK
                // если несколько потоков получают доступ к этому коду, то первый поток зашел,
                // закрыл доступ к коду(критической секции), произвел действия и вышел
                // в это время второй поток не может зайти пока lock не сменит значение,
                // и когда lock освободится, то второй уже увидлит что instance не null
                // `synchronized(lock) { ... }` блокирует `lock`, пока один поток выполняет код внутри блока.
                synchronized(lock) {
                    // если !null, то вернем instance, иначе ничего не сделаем
                    instance?.let {
                        return it
                    }
                    // тут точно ==null, иначе бы предыдущий return сработал
                    // значит нам надо создать объект и его присвоить instance. И also вернет instance
                    return SafeDoubleCheckRepository().also { instance = it }
                }
            } else throw Exception("Invalid passward $password")
        }
    }
}

fun main(){
    val persons = SafeDoubleCheckRepository.getInstance("1").persons
    println(persons)
}