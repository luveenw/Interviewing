package com.interviews.luveen;

import com.google.common.collect.ImmutableSet;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @interview Remix On-Site Coding 2 07/10/2018
 */
public class SentenceReconstruction {
    public static void main(String[] args) {
        /*reconstruct("thequickbrownfox", ImmutableSet.of("the", "quick", "brown", "fox"))
        .forEach(System.out::println);*/

        reconstruct("indefinite", ImmutableSet.of("in", "indefinite"))
        .forEach(System.out::println);
    }

    private static List<String> reconstruct(String str, Set<String> dict) {
        if (str == null || str.isEmpty()) {
            return new ArrayList<>();
        }

        int i = 0;
        StringBuilder sb = new StringBuilder();
        List<String> result;

        while (i < str.length()) {
            sb.append(str.charAt(i));

            if (dict.contains(sb.toString())) {
                try {
                    result = reconstruct(str.substring(i + 1, str.length()), dict);
                    result.add(sb.toString());
                    return result;
                } catch (RuntimeException e) { }
            }

            i++;
        }

        throw new RuntimeException("No valid reconstruction!");
    }
}
