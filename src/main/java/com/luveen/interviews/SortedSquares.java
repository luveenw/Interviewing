package com.luveen.interviews;

import com.google.common.base.Preconditions;

/**
 * Sorts the squares of the values of a sorted integer array containing negative and positive values.
 *
 * Example
 * Input: [-5, -4, 3, -2, -1, 0, 2, 4, 9, 16]
 * Output: [0, 1, 4, 4, 9, 16, 16, 25, 81, 256]
 */
public class SortedSquares {
    long[] sortSquares (int[] a) {
        if (a.length == 0) {
            return new long[0];
        }

        boolean hasNegatives = false;
        int lastNegativeIndex = -1;

        for (int i = 0; i < a.length; i++) {
            if (a[i] < 0) {
                lastNegativeIndex = i;
            }
        }

        if (lastNegativeIndex != -1) {
            hasNegatives = true;
        }

        if (!hasNegatives) {
            return square(a);
        }

        int firstPositiveIndex = lastNegativeIndex + 1;

        long[] first = reverseSquare(a, 0, lastNegativeIndex);
        long[] second = square(a, firstPositiveIndex, a.length - 1);

        return merge(first, second);
    }

    private long[] merge(long[] first, long[] second) {
        int i = 0;
        int j = 0;
        int k = 0;
        long[] result = new long[first.length + second.length];

        while (i < first.length && j < second.length) {
            if (first[i] < second[j]) {
                result[k] = first[i];
                i++;
            }
            else if (first[i] > second[j]) {
                result[k] = second[j];
                j++;
            }
            else {
                result[k] = first[i];
                i++;
                k++;
                result[k] = second[j];
                j++;
            }

            k++;
        }

        if (i < first.length) {
            while (i < first.length) {
                result[k] = first[i];
                i++;
                k++;
            }
        }
        else if (j < second.length) {
            while (j < second.length) {
                result[k] = second[j];
                j++;
                k++;
            }
        }

        return result;
    }

    private long[] square(int[] a) {
        return square(a, 0, a.length - 1);
    }

    private long[] square(int[] a, int start, int end) {
        Preconditions.checkArgument(start <= end);

        long[] result = new long[end - start + 1];

        for (int i = start, k = 0; i <= end; i++, k++) {
            result[k] = a[i] * a[i];
        }

        return result;
    }

    private long[] reverseSquare(int[] a, int start, int end) {
        Preconditions.checkArgument(start <= end);

        int length = end - start + 1;
        long[] result = new long[length];

        for (int i = start, k = length - 1; i <= end; i++, k--) {
            result[k] = a[i] * a[i];
        }

        return result;
    }

    public static void main(String[] args) {
        SortedSquares sortedSquares = new SortedSquares();
        long[] test1 = sortedSquares.sortSquares(new int[]{-5, -4, -2, -1, 0, 0, 3, 4, 5, 6, 7});
        long[] test2 = sortedSquares.sortSquares(new int[] {0});
        long[] test3 = sortedSquares.sortSquares(new int[] {});

        printArray(test1);
        printArray(test2);
        printArray(test3);
    }

    private static void printArray(long[] a) {
        StringBuilder sb = new StringBuilder();

        if (a.length == 0) {
            sb.append("[]");
        }
        else {
            sb.append("[ ");

            for (long x : a) {
                sb.append(x).append(", ");
            }

            sb.delete(sb.length() - 2, sb.length() - 1);
            sb.append(']');
        }

        System.out.println(sb.toString());
    }
}