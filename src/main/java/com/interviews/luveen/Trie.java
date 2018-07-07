package com.interviews.luveen;

import java.util.ArrayList;
import java.util.HashMap;

import static com.interviews.luveen.Printer.*;

/**
 * Mostly from https://github.com/wzhishen/cracking-the-coding-interview/blob/master/src/helpers/Trie.java
 */
public class Trie {
    public static class TrieNode {
        public char character;
        public boolean isWord;
        public Integer rank;
        public HashMap<Character, TrieNode> children;

        public TrieNode(char ch) {
            character = ch;
            isWord = false;
            rank = null;
            children = new HashMap<>();
        }
        public TrieNode() {
            this('\0');
        }
    }

    public static class TrieWord {
        public String word;
        public Integer rank;

        public TrieWord(String word, Integer rank) {
            this.word = word;
            this.rank = rank;
        }

        @Override
        public String toString() {
            return String.format("%s (%d)", word, rank);
        }
    }

    public static void addWord(TrieNode n, String word, int rank) {
        if (word == null || word.isEmpty()) return;
        char[] characters = word.toCharArray();
        for (char ch: characters) {
            if (!n.children.containsKey(ch)) {
                n.children.put(ch, new TrieNode(ch));
            }
            n = n.children.get(ch);
        }
        n.isWord = true;
        n.rank = rank;
    }

    public static ArrayList<TrieWord> getAllWords(TrieNode n) {
        return getWords(n, "");
    }

    public static boolean hasPrefix(TrieNode n, String prefix) {
        return has(n, prefix, false);
    }

    public static boolean hasWord(TrieNode n, String prefix) {
        return has(n, prefix, true);
    }

    private static boolean has(TrieNode n, String prefix, boolean checkHasWord) {
        if (prefix == null) return false;
        char[] characters = prefix.toCharArray();
        for (char ch: characters) {
            if (!n.children.containsKey(ch)) return false;
            n = n.children.get(ch);
        }
        if (checkHasWord) {
            return n.isWord;
        } else {
            return true;
        }
    }

    public static ArrayList<TrieWord> getWords(TrieNode n, String prefix) {
        if (prefix == null) return null;
        ArrayList<TrieWord> result = new ArrayList<>();
        char[] characters = prefix.toCharArray();
        for (char ch: characters) {
            if (!n.children.containsKey(ch)) return result;
            n = n.children.get(ch);
        }
        getWords(n, prefix, result);
        return result;
    }

    private static void getWords(TrieNode n, String word, ArrayList<TrieWord> result) {
        if (n.isWord) result.add(new TrieWord(word, n.rank));
        for (char ch : n.children.keySet()) {
            getWords(n.children.get(ch), word + ch, result);
        }
    }

    private static void printWords(TrieNode n, String prefix) {
        printf("getWords for %s: ", prefix);
        println(getWords(n, prefix));
    }

    public static void printAllWords(TrieNode n) {
        printf("getAllWords: ");
        println(getAllWords(n));
    }
}