package com.interviews.luveen;

import com.google.common.base.Preconditions;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by luvee on 1/25/2017.
 *
 * Determine the longest subsequence that is common to two specified input strings.
 */
public class LongestCommonSubsequence {
    public static void main(String[] args) {
        System.out.println("All subsequences of abc...");
        allSubsequencesOf("abc").forEach(System.out::println);
//        System.out.println("All subsequences of abcdaf...");
//        allSubsequencesOf("abcdaf").forEach(System.out::println);
//        System.out.println("All subsequences of acbcf...");
//        allSubsequencesOf("acbcf").forEach(System.out::println);

//        System.out.println(lcs("abcdaf", "acbcf"));
//        System.out.println(lcs("acbcf", "abcdaf"));
//        System.out.println(lcs("abcd", "aebd"));
//        System.out.println(lcs("abcd", ""));
//        System.out.println(lcs("", "abcd"));
    }

    private static List<String> allSubsequencesOf(String s) {
        if (s == null || s.isEmpty()) {
            return new ArrayList<>();
        }

        List<String> result = new ArrayList<>();
        String c = s.substring(0, 1);
        List<String> subsequences = allSubsequencesOf(s.substring(1));

        result.add(c);
        result.addAll(subsequences);
        result.addAll(subsequences.stream().map(str -> c + str).collect(Collectors.toList()));

        return result;
    }

    private static String lcs(String a, String b) {
        int[][] m = new int[a.length() + 1][b.length() + 1];
        int max = -1;

        for (int i = 1; i <= a.length(); i++) {
            char ac = a.charAt(i - 1);

            for (int j = 1; j <= b.length(); j++) {
                char bc = b.charAt(j - 1);

                if (ac == bc) {
                    m[i][j] = m[i - 1][j - 1] + 1;
                }
                else {
                    m[i][j] = Math.max(m[i - 1][j], m[i][j - 1]);
                }

                if (max < m[i][j]) {
                    max = m[i][j];
                }
            }
        }

//        printStateMatrix(m, a, b);

        return buildLCSFromStateMatrix(m, a, b);
    }

    private static void printStateMatrix(int[][] m, String a, String b) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < a.length() + 1; i++) {
            for (int j = 0; j < b.length() + 1; j++) {
                sb.append(m[i][j]).append("\t");
            }

            sb.append("\n");
        }

        System.out.println(sb.toString());
    }

    private static String buildLCSFromStateMatrix(int[][] m, String a, String b) {
        int aLen = a.length();
        int bLen = b.length();
        int aIdx = aLen;
        int bIdx = bLen;
        int curLen = m[aLen][bLen];
        StringBuilder result = new StringBuilder();

        while (curLen > 0) {
            int above = m[aIdx - 1][bIdx];
            int left = m[aIdx][bIdx - 1];
            int diag = m[aIdx - 1][bIdx - 1];

            if (diag == left && diag == above) {
                result.insert(0, b.charAt(bIdx - 1));
                aIdx--;
                bIdx--;
                curLen--;
            } else if (curLen == above) {
                aIdx--;
            } else {
                bIdx--;
            }
        }

        return result.toString();
    }
}
