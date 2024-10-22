package twoPointers

import org.junit.Assert
import org.junit.Test

class FindNonDuplicateNumberInstancesTest {
    @Test
    fun testExample1() {
        val arr = intArrayOf(2, 3, 3, 3, 6, 9, 9)
        val expected = 4
        val actual = FindNonDuplicateNumberInstances().execute(arr)
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun testExample2() {
        val arr = intArrayOf(2, 2, 2, 11)
        val expected = 2
        val actual = FindNonDuplicateNumberInstances().execute(arr)
        Assert.assertEquals(expected, actual)
    }
}