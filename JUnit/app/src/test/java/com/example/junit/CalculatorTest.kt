package com.example.junit

import org.junit.Test
import org.junit.jupiter.api.Assertions.*


class CalculatorTest {
    @Test
    fun `When 50 add to 100 Then result equals 150`() {
        val actual = Calculator().sum(50, 100)
        val expected = 150
        assertEquals(expected, actual)
    }

    @Test
    fun `When 10 add to 0 Then result equals 10`() {
        val actual = Calculator().sum(10, 0)
        val expected = 10
        assertEquals(expected, actual)
    }

    @Test
    fun `When 100 substruct 10 Then result equals 90`() {
        val actual = Calculator().substruction(100, 10)
        val expected = 90
        assertEquals(expected, actual)
    }

    @Test
    fun `When 10 substruct 0 Then result equals 10`() {
        val actual = Calculator().substruction(10, 0)
        val expected = 10
        assertEquals(expected, actual)
    }

    @Test
    fun `When 5 multiply 10 Then result equals 50`() {
        val actual = Calculator().multiplication(5, 10)
        val expected = 50
        assertEquals(expected, actual)
    }

    @Test
    fun `When 10 multiply 0 Then result equals 0`() {
        val actual = Calculator().multiplication(10, 0)
        val expected = 0
        assertEquals(expected, actual)
    }

    @Test
    fun `When 10 divide 2 Then result equals 5,0`() {
        val actual = Calculator().division(10, 2)
        val expected = 5.0
        assertEquals(expected, actual)
    }

    @Test
    fun `When 0 divide 10 Then result equals 0,0`() {
        val actual = Calculator().division(0, 10)
        val expected = 0.0
        assertEquals(expected, actual)
    }


    @Test
    fun `When 0,01 multiply 100 Then result equals 1(double) with delta 0,001`() {
        val actual = Calculator().multiplication(0.01, 100.0)

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
}