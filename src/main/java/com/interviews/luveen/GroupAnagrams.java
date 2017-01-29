package com.interviews.luveen;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.Lists;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by luvee on 1/20/2017.
 *
 * Given a list of Strings, returns a list of String arrays, with each individual array containing words from the
 * input list that are anagrams of each other.
 */
public class GroupAnagrams {
    public static void main(String[] args) {
        System.out.println(groupAnagrams(Lists.newArrayList("eat", "tea", "tan", "ate", "nat", "bat", "bata", "teat")));
    }

    private static List<List<String>> groupAnagrams(List<String> words) {
        ListMultimap<Map, String> anagramsMap = ArrayListMultimap.create();

        words.forEach(word -> anagramsMap.put(getCharMap(word), word));

        return anagramsMap.keySet().stream().map(anagramsMap::get).collect(Collectors.toList());
    }

    private static Map<Character, Integer> getCharMap(String word) {
        Map<Character, Integer> result = new HashMap<>();

        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            int count = 0;

            if (result.containsKey(c)) {
                count = result.get(c);
            }

            result.put(c, count + 1);
        }

        return result;
    }
}
