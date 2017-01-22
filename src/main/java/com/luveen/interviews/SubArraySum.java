package com.luveen.interviews;

import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by Luveen Wadhwani on 10/23/2016.
 *
 * Determines whether a subarray exists that sums to the specified value in the specified array.
 *
 * Example
 * Input: A = [1, 3, 4, 8, 15]; x = 15
 * Output: true
 * Alternative output: [3, 4, 8]
 */
public class SubArraySum {
    private static int[] findSubarrayWithSum(int[] a, int sum) {
        int curSum = a[0];
        int start = 0;
        int[] result = null;

        for (int i = 1; i <= a.length; i++) {
            while (curSum > sum && start < i - 1) {
                curSum -= a[start];
                start++;
            }

            if (curSum == sum) {
                result = new int[i - start];
                System.arraycopy(a, start, result, 0, i - start);
                break;
            }

            if (i < a.length) {
                curSum += a[i];
            }
        }

        return result;
    }

    public static void main(String[] args) {
        IntStream.of(findSubarrayWithSum(new int[]{1, 3, 5, 8, 10}, 8)).mapToObj(n -> n + " ").forEach(System.out::print);
        System.out.println();

        IntStream.of(findSubarrayWithSum(new int[]{1, 3, 5, 8, 10}, 9)).mapToObj(n -> n + " ").forEach(System.out::print);
        System.out.println();

        IntStream.of(findSubarrayWithSum(new int[]{1, 3, 5, 8, 10}, 18)).mapToObj(n -> n + " ").forEach(System.out::print);
        System.out.println();

        IntStream.of(findSubarrayWithSum(new int[]{1, 3, 5, 8, 10}, 10)).mapToObj(n -> n + " ").forEach(System.out::print);
        System.out.println();

        System.out.println(findSubarrayWithSum(new int[]{1, 3, 5, 8, 10}, 6) == null);
    }
}
