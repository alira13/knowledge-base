package twoPointers;


public class SearchPairIndexesWithTargetSum {
    /*
    Given an array of numbers sorted in ascending order and a target sum,
    find a pair in the array whose sum is equal to the given target.

    Write a function to return the indices of the two numbers (i.e. the pair)
    such that they add up to the given target. If no such pair exists return [-1, -1].
     */
    public int[] execute(int[] arr, int targetSum) {
        int leftPointer = 0;
        int rightPointer = arr.length - 1;

        while (leftPointer != rightPointer) {
            if ((arr[leftPointer] + arr[rightPointer]) == targetSum)
                return new int[]{leftPointer, rightPointer};

            if ((arr[leftPointer] + arr[rightPointer]) > targetSum) {
                rightPointer -= 1;
            } else leftPointer += 1;
        }

        return new int[]{-1, -1};
    }
    //TimeComplex = O(n)
    //SpaceComplex = O(1)
}
