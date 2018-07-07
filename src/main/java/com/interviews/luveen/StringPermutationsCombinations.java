package com.interviews.luveen;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;

public class StringPermutationsCombinations {
    public static void main(String[] args) {
//        List<String> permutations = permute("abcd");
//        printList(permutations);

        List<String> combinations = combine("abcd");
        printList(combinations);
    }

    private static void printList(List<String> list) {
        int i = 0;

        for (String p : list) {
            System.out.println(String.format("String %d: %s", ++i, p));
        }
    }

    static List<String> permute(String s) {
        if (s.length() == 1) {
            return Lists.newArrayList(s);
        }

        List<String> temp = permute(s.substring(1));
        List<String> result = new ArrayList<>();

        for (String t : temp) {
            for (int i = 0; i <= t.length(); i++) {
                result.add(new StringBuilder()
                .append(t, 0, i)
                .append(s, 0, 1)
                .append(t.substring(i))
                .toString());
            }
        }

        return result;
    }

    static List<String> combine(String s) {
        if (s.length() == 1) {
            return Lists.newArrayList(s);
        }

        List<String> temp = combine(s.substring(1));
        List<String> result = new ArrayList<>();
        String substring = s.substring(0, 1);

        result.add(substring);
        result.addAll(temp);

        temp.stream()
                .map(t -> substring + t)
                .forEach(result::add);

        return result;
    }
}
