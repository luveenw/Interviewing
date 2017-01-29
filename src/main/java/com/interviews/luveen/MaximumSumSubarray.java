package com.interviews.luveen;

import java.util.Arrays;

/**
 * Created by luvee on 1/25/2017.
 *
 * Find the subarray in an array that has the maximum sum.
 *
 * @interview LinkedIn Phone 1 12/02/2016
 */
public class MaximumSumSubarray {
    public int[] maxSumSubarray(int[] a) {
        int sum = 0;
        int maxSum = Integer.MIN_VALUE;
        int start = 0;
        int end = 0;
        int resultStart = -1;
        int resultEnd = -1;
        int[] result = new int[0];

        for (int i = 0; i < a.length; i++) {
            sum += a[i];
            end++;

            if (sum <= 0) {
                sum = 0;
                start = i + 1;
                end = i + 1;
            }

            if (maxSum < sum) {
                maxSum = sum;
                resultStart = start;
                resultEnd = end;
            }
        }

        if (maxSum != Integer.MIN_VALUE) {
            result = new int[resultEnd - resultStart];

            System.arraycopy(a, resultStart, result, 0, resultEnd - resultStart);
        }

        return result;
    }

    public static void main(String[] args) {
        MaximumSumSubarray s = new MaximumSumSubarray();

        Arrays.stream(s.maxSumSubarray(new int[]{})).forEach(System.out::print);
        System.out.println();

        Arrays.stream(s.maxSumSubarray(new int[]{-2, -3, 4, -1, -2, 1, 5, -3})).mapToObj(n -> n + " ").forEach(System.out::print);
        System.out.println();
    }
}
