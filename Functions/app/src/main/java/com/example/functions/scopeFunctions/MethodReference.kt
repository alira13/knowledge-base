package com.example.functions.scopeFunctions

fun main() {
    val list = listOf(1,2,3)

    // можем писать так
    list.forEach{
        println(it)
    }

    // а можем сделать ссылку на метод println, чтобы не создавать свою lambda
    list.forEach(::println)
}
