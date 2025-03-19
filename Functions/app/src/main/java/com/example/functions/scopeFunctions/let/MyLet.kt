package com.example.functions.scopeFunctions.let

// Вызывает функцию lambda c аргументом this и возвращает результат
// может быть вызвала и у not null значения

// ПОЗВОЛЬ выполнить дейтсвие над ОБЪЕКТОМ и верни результат
inline fun <T, R> T.myLet(lambda: (T) -> R): R {
    return lambda(this)
}

var age: Int? = null
var notNullAge = 5

fun main() {
    age = 5

    //наша реализация let
    val myLetString = age?.myLet {
        if (it >= 18)
            "Is adult"
        else
            "Will be adult in ${18 - it} years"
    }
    println(myLetString)

    // реализация Kotlin
    val letString = age?.let {
        if (it >= 18)
            "Is adult"
        else
            "Will be adult in ${18 - it} years"
    }
    println(letString)


    //вызов у notNull
    // реализация Kotlin
    val notNullLetString = notNullAge.let {
        if (it >= 18)
            "Is adult"
        else
            "Will be adult in ${18 - it} years"
    }
    println(notNullLetString)

    val ageAsString = age.let { println() }

}