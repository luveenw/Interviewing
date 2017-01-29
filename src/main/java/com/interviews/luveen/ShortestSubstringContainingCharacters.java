package com.interviews.luveen;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by luvee on 1/20/2017.
 *
 * Determine the shortest substring of a specified string that contains all characters in another specified string.
 * @interview Uber Phone 1 1/20/2017
 */
public class ShortestSubstringContainingCharacters {
    public static void main(String[] args) {
//        System.out.println("1 " + findShortestSubstring("", ""));
        System.out.println("2 " + findShortestSubstring("xCxxAxxAxxCxAxBx", "ABC"));
        System.out.println("3 " + findShortestSubstring("xCxxAxxAxxCxAxBx", "ABCAADEAB"));
        System.out.println("4 " + findShortestSubstring("xCxDxAxAxBAxBExCxAx", "ABCAADEAB"));
        System.out.println("5 " + findShortestSubstring("ABC", "xCxxAxxAxxCxAxBx"));
    }

    private static String findShortestSubstring(String source, String target) {
        // if the target string to be found is
        // 1. empty, or
        // 2. longer than the source string in which to find it,
        // return empty
        if (target.isEmpty() || target.length() > source.length()) {
            return "";
        }

        Map<Character, Integer> targetCounts = buildCharCountsFor(target);
        Map<Character, Integer> sourceCounts = new HashMap<>();
        int targetLen = target.length();
        int numTargetCharsFoundInSource = 0;
        int left = 0;
        int minLen = Integer.MAX_VALUE;
        String result = "";

        for (int i = 0; i < source.length(); i++) {
            char c = source.charAt(i);

            // Analyze each character in the source, looking for matches with the target
            if (targetCounts.containsKey(c)) {
                int n = 0;

                if (sourceCounts.containsKey(c)) {
                    n = sourceCounts.get(c);

                    // If we found a required character in the source, and we haven't found as many as we need to form
                    // the target, count this character
                    if (n < targetCounts.get(c)) {
                        numTargetCharsFoundInSource++;
                    }
                }
                else {
                    // If we found a required character that we haven't found any of yet, count this character
                    numTargetCharsFoundInSource++;
                }

                sourceCounts.put(c, n + 1);
            }

            if (numTargetCharsFoundInSource == targetLen) {
                char sc = source.charAt(left);

                while (!sourceCounts.containsKey(sc)
                        || (sourceCounts.containsKey(sc) && sourceCounts.get(sc) > targetCounts.get(sc))) {

                    if (sourceCounts.containsKey(sc) && sourceCounts.get(sc) > targetCounts.get(sc)) {
                        sourceCounts.put(sc, sourceCounts.get(sc) - 1);
                    }

                    left++;
                    sc = source.charAt(left);
                }

                if (minLen > i - left + 1) {
                    result = source.substring(left, i + 1);
                    minLen = i - left + 1;
                }
            }
        }

        return result;
    }

    // construct frq map for chars in s
    private static Map<Character, Integer> buildCharCountsFor(String s) {
        Map<Character, Integer> result = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            int count = 0;
            char c = s.charAt(i);

            if (result.containsKey(c)) {
                count = result.get(c);
            }

            result.put(c, count + 1);
        }

        return result;
    }
}
