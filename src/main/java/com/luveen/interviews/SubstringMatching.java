package com.luveen.interviews;

/**
 * Determine whether a supplied regex pattern would match a supplied string.
 *
 * Example
 * 1) Input: pattern - a*xy?z  string - almnxybz
 *    Output: true
 * 2) Input: pattern - a*xy?z  string - axyz
 *    Output: false
 *
 */
public class SubstringMatching {
/*    private static boolean recursiveMatchHelper;

    public static void main(String[] args) {
        System.out.println(String.format(
                "Does pattern %s match %s? %b", "a*xy?z", "almnxybz",
                isRecursiveMatch("a*xy?z", "almnxybz")));

        System.out.println(String.format(
                "Does pattern %s match %s? %b", "a*xy?z", "axyz",
                isRecursiveMatch("a*xy?z", "axyz")));
    }

    private static boolean isRecursiveMatch(String pattern, String str) {
        String pattern1 = removeRepeatedStars(pattern);

        return isRecursiveMatchHelper(pattern1, str, 0, 0);
    }

    private static String removeRepeatedStars(String pattern) {
        StringBuilder sb = new StringBuilder();
        boolean isFirstStar = true;

        for (int i = 0; i < pattern.length(); i++) {
            char c = pattern.charAt(i);

            if (c == '*') {
                if (isFirstStar) {
                    isFirstStar = false;
                    sb.append(c);
                }
            }
            else {
                isFirstStar = true;
                sb.append(c);
            }
        }

        return sb.toString();
    }

    private static boolean isRecursiveMatchHelper(String pattern, String str, int p, int s) {
        if ()
        return recursiveMatchHelper;
    }*/
}
