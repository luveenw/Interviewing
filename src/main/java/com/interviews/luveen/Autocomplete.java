package com.interviews.luveen;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static com.interviews.luveen.Trie.*;

public class Autocomplete {

    private static final String FILE_NAME = "sample-input_1.txt";

    public static void main(String args[]) throws FileNotFoundException {
        ProcessingResult result = buildTrieFrom(FILE_NAME);

        printAllWords(result.root);

        for (String prefix : result.getPrefixes()) {
            List<TrieWord> words = getWords(result.root, prefix);
            words.sort(Comparator.comparingInt(w -> w.rank));
            words = words.size() > 5 ? words.subList(0, 5) : words;
            System.out.println(prefix + ":");
            words.forEach(System.out::println);
            System.out.println();
        }
    }

    private static ProcessingResult buildTrieFrom(String fileName) throws FileNotFoundException {
        Reader reader = new FileReader(fileName);
        int numWords = -1;
        int numPrefixes = -1;
        TrieNode root = new TrieNode();
        List<String> prefixes = new ArrayList<>();

        try {
            BufferedReader in = new BufferedReader(reader);
            int lineNum = 1;

            while(in.ready()) {
                String input = in.readLine();

                if (lineNum <= 2) {
                    if (lineNum == 1) {
                        numWords = Integer.valueOf(input);
                    } else {
                        numPrefixes = Integer.valueOf(input);
                    }
                }
                else if (lineNum <= 2 + numWords) {
                    System.out.println(input);
                    addWord(root, input, lineNum - 2);

                } else {
                    prefixes.add(input);
                }
                lineNum++;
            }
        }  catch (IOException e) {
            // There was an input error.
        }

        System.out.println("numWords: " + String.valueOf(numWords));
        System.out.println("numPrefixes: " + String.valueOf(numPrefixes));

        return new ProcessingResult(root, prefixes);
    }

    private static class ProcessingResult {
        private List<String> prefixes;
        private TrieNode root;

        public ProcessingResult(TrieNode root, List<String> prefixes) {
            this.prefixes = prefixes;
            this.root = root;
        }

        public List<String> getPrefixes() {
            return prefixes;
        }

        public void setPrefixes(List<String> prefixes) {
            this.prefixes = prefixes;
        }

        public TrieNode getRoot() {
            return root;
        }

        public void setRoot(TrieNode root) {
            this.root = root;
        }
    }
}
