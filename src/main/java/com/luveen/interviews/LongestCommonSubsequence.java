package com.luveen.interviews;

import com.google.common.base.Preconditions;

/**
 * Created by luvee on 1/25/2017.
 *
 * Determine the longest subsequence that is common to two specified input strings.
 */
public class LongestCommonSubsequence {
    public static void main(String[] args) {
        System.out.println(lcs("abcdaf", "acbcf"));
        System.out.println(lcs("abcd", "aebd"));
        System.out.println(lcs("abcd", ""));
    }

    private static String lcs(String a, String b) {
        int[][] m = new int[a.length() + 1][b.length() + 1];
        StringBuilder sb = new StringBuilder();
        int max = -1;

        for (int i = 1; i <= a.length(); i++) {
            for (int j = 1; j <= b.length(); j++) {
                char ac = a.charAt(i - 1);
                char bc = b.charAt(j - 1);

                if (ac == bc) {
                    m[i][j] = m[i - 1][j - 1] + 1;
                }
                else {
                    if (m[i - 1][j] > m[i][j - 1]) {
                        m[i][j] = m[i - 1][j];
                    }
                    else {
                        m[i][j] = m[i][j - 1];
                    }
                }

                if (max < m[i][j]) {
                    max = m[i][j];
                    sb.append(bc);
                }
            }
        }

        Preconditions.checkArgument(max == -1 || max == sb.length());

        return sb.toString();
    }
}
