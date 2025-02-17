package com.example.functions.scopeFunctions

import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

// C ЭТИМ ОБЪЕКТОМ произведи какие-то действия и верни результат
inline fun <T, R> myWith(element: T, lambda: T.() -> R): R {
    return element.lambda()
}


fun main() {
    with(mutableListOf<Int>(), {
        while (true) {
            val num = readln().toInt().takeIf { it != 0 } ?: break
            // так как используем apply, то it не нужно,
            // мы как будто находимся в классе MutableList, т.е можно использовать this
            add(num)
        }
        println("Max value=${max()}")
        println("Min value=${min()}")
        this

    }).forEach { println(it) }
}

