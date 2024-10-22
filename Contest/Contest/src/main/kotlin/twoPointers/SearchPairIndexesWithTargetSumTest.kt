package twoPointers

import org.junit.Assert
import org.junit.Test

class SearchPairIndexesWithTargetSumTest {
    @Test
    fun testExample1(){
        val expected = intArrayOf(1,3)
        val arr = intArrayOf(1, 2, 3, 4, 6)
        val target = 6
        val result = SearchPairIndexesWithTargetSum()
            .execute(arr, target)
        Assert.assertArrayEquals(expected, result)
    }

    @Test
    fun testExample2(){
        val expected = intArrayOf(0,2)
        val arr = intArrayOf(2, 5, 9, 11)
        val target = 11
        val result = SearchPairIndexesWithTargetSum()
            .execute(arr, target)
        Assert.assertArrayEquals(expected, result)
    }
}