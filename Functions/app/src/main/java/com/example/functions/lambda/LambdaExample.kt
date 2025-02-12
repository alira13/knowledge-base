package com.example.functions.lambda

fun main() {
    val lambda1: (String) -> Unit = {
        val a = 1
        println(it + a.toString())
    }

    val lambda2: (String) -> Int = { someText ->
        val a = 2
        println(someText + a.toString())
        a
    }

    lambda1("Hello")
    val a = lambda2("Buy")
    println(a)

    // описываем лямбду за круглыми скобками в фигурных
    filter("Hi") {
        println(it)
    }
}

// функция, которая вызывает другую функцию
fun filter(item: String, lambda: (String) -> Unit) {
    lambda(item)
}