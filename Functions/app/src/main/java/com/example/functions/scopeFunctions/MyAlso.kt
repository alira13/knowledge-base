package com.example.functions.scopeFunctions

// выполни ТАКЖЕ какое-то действие НЕ ИЗМЕНЯЯ объект, верни сам объект
inline fun <T> T.myAlso(lambda: (T) -> Unit): T {
    lambda(this)
    return this
}

fun main() {
    val items = listOf(1, 2, 3)

    // пропускает через себя элементы и никак их не меняет
    // просто их возвращает и совершает еще какое-то действие,
    // например логирует
    val myAlsoItems = items.myAlso {
        println("Call also $it")
    }

    println(myAlsoItems)

    val alsoItems = items.also {
        it + 2
        println("Call also $it")
    }
    println(alsoItems)
}