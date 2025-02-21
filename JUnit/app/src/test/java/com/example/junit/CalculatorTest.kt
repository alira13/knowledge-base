package com.example.junit

import junit.framework.Assert.assertEquals
import org.junit.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource


class CalculatorTest {

    @ParameterizedTest
    @MethodSource("calculatorSource")
    fun `When 50 add to 100 Then result equals 150`(calculator: Calculator) {
        val actual = calculator.sum(50, 100)
        val expected = 151
        assertEquals(expected, actual)
    }

    @ParameterizedTest
    @MethodSource("calculatorSource")
    fun `When 10 add to 0 Then result equals 10`(calculator: Calculator) {
        val actual = calculator.sum(10, 0)
        val expected = 10
        assertEquals(expected, actual)
    }

    @ParameterizedTest
    @MethodSource("calculatorSource")
    fun `When 100 substruct 10 Then result equals 90`(calculator: Calculator) {
        val actual = calculator.subtraction(100, 10)
        val expected = 90
        assertEquals(expected, actual)
    }

    @ParameterizedTest
    @MethodSource("calculatorSource")
    fun `When 10 substruct 0 Then result equals 10`(calculator: Calculator) {
        val actual = calculator.subtraction(10, 0)
        val expected = 10
        assertEquals(expected, actual)
    }

    @ParameterizedTest
    @MethodSource("calculatorSource")
    fun `When 5 multiply 10 Then result equals 50`(calculator: Calculator) {
        val actual = calculator.multiplication(5, 10)
        val expected = 50
        assertEquals(expected, actual)
    }

    @ParameterizedTest
    @MethodSource("calculatorSource")
    fun `When 10 multiply 0 Then result equals 0`(calculator: Calculator) {
        val actual = calculator.multiplication(10, 0)
        val expected = 0
        assertEquals(expected, actual)
    }

    @ParameterizedTest
    @MethodSource("calculatorSource")
    fun `When 10 divide 2 Then result equals 5,0`(calculator: Calculator) {
        val actual = calculator.division(10, 2)
        val expected = 5.0
        assertEquals(expected, actual)
    }

    @ParameterizedTest
    @MethodSource("calculatorSource")
    fun `When 0 divide 10 Then result equals 0,0`(calculator: Calculator) {
        val actual = calculator.division(0, 10)
        val expected = 0.0
        assertEquals(expected, actual)
    }


    @Test
    fun `When 0,01 multiply 100 Then result equals 1(double) with delta 0,001`() {
        val actual = 0.01 * 100

        //должно быть 1, но не 1 на самом деле, а 1.0000000000000007
        // Нужно погрешность указывать
        var aDouble = 0.0
        repeat(100) {
            aDouble += 0.01
        }

        val expected = aDouble
        val delta = 0.001

        assertEquals(expected, actual, delta)
    }

    companion object {
        @JvmStatic
        fun calculatorSource() = listOf(SimpleCalculator(), LoggingCalculator())
    }
}