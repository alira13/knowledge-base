package slidingWindow;

import java.util.Arrays;

public class FindMaximumSumSubarrayOfSizeK {
    public int execute(int[] arr, int k) {
        double[] resultArray = new double[k];

        int startWindowIndex = 0;
        int sum = 0;
        int maxSum = 0;
        int resultStartIndex = 0;

        for (int endWindowIndex = 0; endWindowIndex < arr.length; endWindowIndex++) {
            // 1. логика для элементов внутри окна
            sum += arr[endWindowIndex]; // просто считаем сумму для эл-тов 1 окна

            // 2. окно закончилось
            if (endWindowIndex >= k - 1) { // встали на последний элемент окна

                // 2. основная логика после окончания окна
                if (sum > maxSum) {
                    maxSum = sum; // перепишем значение максимальной суммы
                    resultStartIndex = startWindowIndex; //запомним стартовый индекс с максимальной суммой
                }

                // 2. сдвигаем окно после окончания окна
                sum -= arr[startWindowIndex]; //подготовим для следующей операции значение суммы
                startWindowIndex++; // сдвинем начало окна
            }
        }

        for(int i=0; i<k; i++){
            resultArray[i]= arr[resultStartIndex+i];
        }
        System.out.println(Arrays.toString(resultArray));
        return maxSum;
    }
}
