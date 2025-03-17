package com.example.valueClass

private fun main() {
    // можно ошибиться и засунуть просто какое-то левое число Int
    val person = Person(1)

    // так более осознанно используем id
    val worker = Worker(WorkerId(2))
    val wi = WorkerId(2)
}

private data class Person(val id: Int)


private data class Worker(val id: WorkerId)

// раньше были value и inline разные вещи,
// value - просто обертка класса с 1 полем
// @JvmInline отвечала за встраивание кода как и у inline-функций без создания доп объекта
// но сейчас работают только в связке JvmInline+value
@JvmInline
private value class WorkerId(val value: Int)