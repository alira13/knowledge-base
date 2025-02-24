package com.example.collections

import com.example.collections.mutableSet.MyHashSetImpl
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test


class MyMutableHashSetImplTest {

    @Test
    fun `initial myArrayList size is 0`() {
        val myHashSet = MyHashSetImpl()

        val actual = myHashSet.size
        val expected = 0
        assertEquals(expected, actual)
    }

    @Test
    fun `When add 1 elements them size is 1`() {
        val myHashSet = MyHashSetImpl()

        myHashSet.add(0)
        val actual = myHashSet.size
        val expected = 1
        assertEquals(expected, actual)
    }

    @Test
    fun `When add 1 elements then return true`() {
        val myHashSet = MyHashSetImpl()

        val actual = myHashSet.add(0)
        assertTrue(actual)
    }

    @Test
    fun `When add 1 equals elements then return false`() {
        val myHashSet = MyHashSetImpl()
        myHashSet.add(0)
        val actual = myHashSet.add(0)
        assertFalse(actual)
    }

    @Test
    fun `When add 10 elements then size is 10`() {
        val myHashSet = MyHashSetImpl()

        repeat(10) { myHashSet.add(it) }
        val actual = myHashSet.size
        val expected = 10
        assertEquals(expected, actual)
    }

    @Test
    fun `When add 10 equals elements then size is 1`() {
        val myHashSet = MyHashSetImpl()

        repeat(10) { myHashSet.add(2) }
        val actual = myHashSet.size
        val expected = 1
        assertEquals(expected, actual)
    }

    @Test
    fun `When add value 1000 then hashSet contains 1000`() {
        val myHashSet = MyHashSetImpl()

        repeat(10) { myHashSet.add(it) }
        myHashSet.add(1000)
        val actual = myHashSet.contains(1000)

        assertTrue(actual)
    }

    @Test
    fun `When dont add value 1000 then hashSet contains 1000`() {
        val myHashSet = MyHashSetImpl()
        repeat(10) { myHashSet.add(it) }
        val actual = myHashSet.contains(1000)
        assertFalse(actual)
    }

    @Test
    fun `When add 100 elements and then remove 50 value then size wil be 99`() {
        val myHashSet = MyHashSetImpl()

        repeat(100) { myHashSet.add(it) }
        myHashSet.remove(50)
        val actual = myHashSet.size
        val expected = 99
        assertEquals(expected, actual)
    }

    @Test
    fun `When remove 50 value element then hashSet does not contain 50 value`() {
        val myHashSet = MyHashSetImpl()
        repeat(100) { myHashSet.add(it) }
        myHashSet.remove(50)
        val actual = myHashSet.contains(50)
        assertFalse(actual)
    }

    @Test
    fun `When list is cleared then size is 0`() {
        val myHashSet = MyHashSetImpl()

        repeat(100) { myHashSet.add(it) }
        myHashSet.clear()
        val actual = myHashSet.size
        val expected = 0
        assertEquals(expected, actual)
    }

    @Test
    fun `When add 100 elements and then contains 50 is true`() {
        val myHashSet = MyHashSetImpl()

        repeat(100) { myHashSet.add(it) }
        myHashSet.contains(50)
        val actual = myHashSet.contains(50)
        assertTrue(actual)
    }

    @Test
    fun `When add 100 elements and then contains 100 is false`() {
        val myHashSet = MyHashSetImpl()

        repeat(100) { myHashSet.add(it) }
        val actual = myHashSet.contains(100)
        assertFalse(actual)
    }
}