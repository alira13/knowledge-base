package com.example.junit

class Calculator {
    fun sum(a: Int, b: Int) = a + b

    fun sum(a: Double, b: Double) = a + b

    fun substruction(a: Int, b: Int) = a - b

    fun substruction(a: Double, b: Double) = a - b


    fun multiplication(a: Int, b: Int) = a * b

    fun multiplication(a: Double, b: Double) = a * b


    fun division(a: Int, b: Int):Double = a.toDouble() / b

    fun division(a: Double, b: Double) = a / b

}