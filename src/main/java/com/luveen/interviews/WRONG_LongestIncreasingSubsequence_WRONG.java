package com.luveen.interviews;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by luvee on 1/24/2017.
 *
 * Find the the longest increasing subsequence in an integer array. Addtionally: Alternative solution to return only
 * the length of the longest increasing subsequence.
 */
public class WRONG_LongestIncreasingSubsequence_WRONG {
    public static void main(String[] args) {
        Arrays.stream(longestIncreasingSubsequenceIn(new int[] {9, 1, 3, 7, 5, 6, 20})).forEach(System.out::print);
        System.out.println();
        Arrays.stream(longestIncreasingSubsequenceIn(new int[] {3, 4, -1, 0, 6, 2, 3})).forEach(System.out::print);
        System.out.println();
        Arrays.stream(longestIncreasingSubsequenceIn(new int[]{2, 5, 1, 8, 3})).forEach(System.out::print);
        System.out.println();
    }

    static int[] longestIncreasingSubsequenceIn(int[] arr) {
        int len = arr.length;
        List<Integer> seq = new ArrayList<Integer>();

        for (int n : arr) {
            if (seq.size() == 0 || n > seq.get(seq.size() - 1)) {
                // seq is empty, or n satisfies the increasing subsequence constraint. Add n to the end of seq.
                seq.add(n);
            }
            else {
                // n does not satisfy the increasing subsequence constraint. Look for the smallest element that is larger
                // than or equal to n, and set
                int i = 0;
                int j = seq.size() - 1;

                while (i < j) {
                    int mid = (i + j) / 2;

                    if (seq.get(mid) < n) {
                        i = mid + 1;
                    }
                    else {
                        j = mid;
                    }
                }

                seq.set(j, n);
            }
        }

        return seq.stream().mapToInt(Integer::intValue).toArray();
    }

    static int lengthOfLongestIncreasingSubsequence(int[] arr) {
        return -1;
    }
}
