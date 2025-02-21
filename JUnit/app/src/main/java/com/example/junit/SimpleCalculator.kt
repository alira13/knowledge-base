package com.example.junit

class SimpleCalculator : Calculator {
    override fun sum(a: Int, b: Int) = a + b

    fun sum(a: Double, b: Double) = a + b

    override fun subtraction(a: Int, b: Int) = a - b

    fun subtraction(a: Double, b: Double) = a - b


    override fun multiplication(a: Int, b: Int) = a * b

    fun multiplication(a: Double, b: Double) = a * b


    override fun division(a: Int, b: Int): Double = a.toDouble() / b

    fun division(a: Double, b: Double) = a / b

}