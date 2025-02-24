package com.example.collections

import com.example.collections.mutableCollection.mutableList.arrayList.MyMutableArrayListImpl
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test


class MyMutableArrayListImplTest {

    private var myArrayList = MyMutableArrayListImpl<Int>()

    @Test
    fun `initial myArrayList size is 0`() {
        val actual = myArrayList.size
        val expected = 0
        assertEquals(expected, actual)
    }

    @Test
    fun `When add 1 elements them size is 1`() {
        myArrayList.add(0)
        val actual = myArrayList.size
        val expected = 1
        assertEquals(expected, actual)
    }

    @Test
    fun `When add 10 elements them size is 10`() {
        repeat(10) { myArrayList.add(it) }
        val actual = myArrayList.size
        val expected = 10
        assertEquals(expected, actual)
    }

    @Test
    fun `When add 10 elements and get 5th result is 5`() {
        repeat(10) { myArrayList.add(it) }
        val actual = myArrayList.get(5)
        val expected = 5
        assertEquals(expected, actual)
    }

    @Test
    fun `When add 100 elements them size is 100`() {
        repeat(100) { myArrayList.add(it) }
        val actual = myArrayList.size
        val expected = 100
        assertEquals(expected, actual)
    }

    @Test
    fun `When add 100 elements and get 50th result is 50`() {
        repeat(100) { myArrayList.add(it) }
        val actual = myArrayList.get(50)
        val expected = 50
        assertEquals(expected, actual)
    }

    @Test
    fun `When add 100 elements and get 50th element with indexing operator result is 50`() {
        repeat(100) { myArrayList.add(it) }
        val actual = myArrayList[50]
        val expected = 50
        assertEquals(expected, actual)
    }

    @Test
    fun `When element added to first position then it is in first position`() {
        myArrayList.clear()
        repeat(10) { myArrayList.add(it) }
        myArrayList.add(0, 1000)

        val actual = myArrayList.get(0)
        val expected = 1000
        assertEquals(expected, actual)
    }

    @Test
    fun `When element added to last position then it is in last position`() {
        repeat(100) { myArrayList.add(it) }
        myArrayList.add(100, 1000)
        val actual = myArrayList.get(100)
        val expected = 1000
        assertEquals(expected, actual)
    }

    @Test
    fun `When add 100 elements and Then remove 50th element then size wil be 99`() {
        repeat(100) { myArrayList.add(it) }
        myArrayList.removeAt(50)
        val actual = myArrayList.size
        val expected = 99
        assertEquals(expected, actual)
    }

    @Test
    fun `When remove 50th element Then 50th value will be 51`() {
        repeat(100) { myArrayList.add(it) }
        myArrayList.removeAt(50)
        val actual = myArrayList.get(50)
        val expected = 51
        assertEquals(expected, actual)
    }

    @Test
    fun `When remove element with value 50 Then 50th value will be 51`() {
        repeat(100) { myArrayList.add(it) }
        myArrayList.remove(50)
        val actual = myArrayList.get(50)
        val expected = 51
        assertEquals(expected, actual)
    }

    @Test
    fun `When remove with operator element with value 50 Then 50th value will be 51`() {
        repeat(100) { myArrayList.add(it) }
        myArrayList - 50
        val actual = myArrayList.get(50)
        val expected = 51
        assertEquals(expected, actual)
    }

    @Test
    fun `When list is cleared then size is 0`() {
        repeat(100) { myArrayList.add(it) }
        myArrayList.clear()
        val actual = myArrayList.size
        val expected = 0
        assertEquals(expected, actual)
    }

    @Test
    fun `When add 100 elements and then contains 50 is true`() {
        repeat(100) { myArrayList.add(it) }
        myArrayList.contains(50)
        val actual = myArrayList.contains(50)
        assertTrue(actual)
    }

    @Test
    fun `When add 100 elements and then contains 100 is false`() {
        repeat(100) { myArrayList.add(it) }
        val actual = myArrayList.contains(100)
        assertFalse(actual)
    }
}