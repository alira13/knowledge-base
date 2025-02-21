package com.example.junit

class LoggingCalculator : Calculator {

    override fun sum(a: Int, b: Int) = let {
        println("Get sum")
        a + b
    }

    override fun subtraction(a: Int, b: Int) = let {
        println("Get sum")
        a - b
    }

    override fun multiplication(a: Int, b: Int) = let {
        println("Get multiplication")
        a * b
    }

    override fun division(a: Int, b: Int): Double = let {
        println("Get division")
        a.toDouble() / b
    }
}