package com.example.functions.infix

private fun main() {
    val myList1 = listOf(1.myTo("One"), 2.myTo("Two"))
    val myList2 = listOf(1 myTo "O", 2 myTo "T")
    myList1.forEach {
        println(it)
    }

    myList2.forEach {
        println(it)
    }
}

// инфиксная форма просто позволяет написать тип расширения_имя функции расширения_аргумент
private infix fun <F, S> F.myTo(second: S): Pair<F, S> {
    return Pair(this, second)
}


