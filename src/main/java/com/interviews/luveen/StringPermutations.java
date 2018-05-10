package com.interviews.luveen;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;

public class StringPermutations {
    public static void main(String[] args) {
//        Lists.newArrayList("abcd").forEach(System.out::println);

        List<String> result = permute("abcd");
        int i = 0;

        for (String p : result) {
            System.out.println(String.format("String %d: %s", ++i, p));
        }
    }

    private static List<String> permute(String s) {
        if (s.length() == 1) {
            return Lists.newArrayList(s);
        }

        List<String> temp = permute(s.substring(1));
        List<String> result = new ArrayList<>();

        for (String t : temp) {
            for (int i = 0; i <= t.length(); i++) {
                String substring = t.substring(0, i);
                String substring1 = s.substring(0, 1);
                String substring2 = t.substring(i);

                result.add(substring + substring1 + substring2);
            }
        }

        return result;
    }
}
