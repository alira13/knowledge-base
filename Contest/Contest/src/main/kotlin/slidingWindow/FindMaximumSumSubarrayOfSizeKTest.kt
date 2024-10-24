package slidingWindow

import org.junit.Assert
import org.junit.Test

class FindMaximumSumSubarrayOfSizeKTest {
    @Test()
    fun testExample1() {
        val arr = intArrayOf(2, 1, 5, 1, 3, 2)
        val k = 3
        val expected = 9
        Assert.assertEquals(expected, FindMaximumSumSubarrayOfSizeK().execute(arr, k))
    }

    @Test()
    fun testExample2() {
        val arr = intArrayOf(2, 3, 4, 1, 5)
        val k = 2
        val expected = 7
        Assert.assertEquals(expected, FindMaximumSumSubarrayOfSizeK().execute(arr, k))
    }
}