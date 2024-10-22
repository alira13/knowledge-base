package twoPointers;

import java.util.Arrays;


public class FindNonDuplicateNumberInstances {
    /*Given an array of sorted numbers,
    move all non-duplicate number instances at the beginning of the array in-place.
    The non-duplicate numbers should be sorted and you should not use any extra space
    so that the solution has constant space complexity i.e., .

    Move all the unique number instances at the beginning of the array
    and after moving return the length of the subarray that has no duplicate in it.
    */
    public int execute(int[] arr) {
        int nextNonDublicate = 1;

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] != arr[i - 1]) {
                arr[nextNonDublicate] = arr[i];
                nextNonDublicate++;
            }
        }
        System.out.println(Arrays.toString(arr));
        return nextNonDublicate;
    }
    //TimeComplex = O(n)
    //SpaceComplex = O(1)
}
