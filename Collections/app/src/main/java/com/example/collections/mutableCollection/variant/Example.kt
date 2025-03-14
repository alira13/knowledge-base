package com.example.collections.mutableCollection.variant

import com.example.collections.mutableCollection.myCollection.MyList
import com.example.collections.mutableCollection.myCollection.myListOf

private fun main() {
    //совместимость присваивания - когда в переменную общего типа можно положить объект частного типа
    val worker: Worker = Programmer("John")
    showName(worker)

    // ковариантность MyList<out T> - дело в out. Неизменяемый тип безопасно делать ковариантным
    val workers: MyList<Worker> = myListOf<Programmer>(Programmer("Nick"), Programmer("Natalie"))
    showCount(workers)

    // инвариантность MutableList<E>. Изменяемые типы нельзя делать ковариантными
    //val mutableWorkers: MutableList<Worker> = mutableListOf()<Programmer>(Programmer("Nick"), Programmer("Natalie"))
}

// Пример с ошибкой. Когда сделали изменяемый тип ковариантным и к чему это приведет
// Мы можем привести к родительскому классу any и засунуть абсолютно любой объект в коллекцию с работниками
// И при обращении к колекции возникнет ошибка, потому что в коллекции могут быть только работники А мы засунули Hello
private fun tryToMakeCovariant() {
    /*
    val mutableWorkers: MyMutableList<Worker> =
        myMutableListOf<Programmer>(Programmer("Nick"), Programmer("Natalie"))
    val any: MyMutableList<Any> = mutableWorkers
    any.add("Hello")
    println(any)
     */
}

private fun showName(worker: Worker) {
    println(worker.name)
}

private fun showCount(workers: MyList<Worker>) {
    println(workers.size)
}

// Worker - исходный тип
// MyList<Worker> - производный тип от Worker
open class Worker(val name: String)

// Programmer - исходный тип
// MyList<Worker> - производный тип от Worker
class Programmer(name: String) : Worker(name)