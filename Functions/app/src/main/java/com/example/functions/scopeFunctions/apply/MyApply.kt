package com.example.functions.scopeFunctions.apply

// Сама является функцией расширения класса T
// Принимает на вход функцию, которая расширяет класс T
// В итоге мы можем работать с телом функции myApply,
// как будто мы находитмся внутри класса T

// ПРИМЕНИк объекту внутри какие-то действия, ИЗМЕНИ ЕГО и верни этот ОБЪЕКТ
inline fun <T> T.myApply(lambda: T.() -> Unit): T {
    lambda()
    return this
}

fun main() {
    mutableListOf<Int>()
        .myApply {
            while (true) {
                val num = readln().toInt().takeIf { it != 0 } ?: break
                // так как используем apply, то it не нужно,
                // мы как будто находимся в классе MutableList, т.е можно использовать this
                add(num)
            }
        }
        .forEach { println(it) }
}