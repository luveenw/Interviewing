package com.interviews.luveen;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/*
"please sit spot. sit spot, sit. spot here now here."

1. If a word anywhere in the text is preceded by a comma, find all occurrences of that word in the text,
and put a comma before each of those occurrences, except in the case where such an occurrence
is the first word of a sentence or already preceded by a comma.

"please, sit spot. sit spot, sit. spot here now here."

2. If a word anywhere in the text is succeeded by a comma, find all occurrences of that word in the
text, and put a comma after each of those occurrences, except in the case where such an occurrence
is the last word of a sentence or already succeeded by a comma.

"please, sit spot. sit spot, sit. spot, here now here."

3. Apply rules 1 and 2 repeatedly until no new commas can be added using either of them

"please, sit spot. sit spot, sit. spot, here now, here."

"please, sit spot. sit spot, sit. spot, here now, here."

@interview Interviewing.io Practice Interview 5/4/2018
 */
public class SprinkleCommas {
    private static final char COMMA = ',';
    private static final char PERIOD = '.';
    private static final char NONE = '\0';
    private static final String SPLIT_AFTER_COMMA = " ";
    private static final String SPLIT_BEFORE_COMMA = " ";

    public static void main(String[] args) {
        ArrayList<String> strings = new ArrayList<>();
        strings.add("please sit spot. sit spot, sit. spot here now here.");
//        strings.add("please now sit spot. sit spot, sit. spot here now here.");

        for (String string : strings) {
//            System.out.println(addSucceedingCommasIn(string));
//            System.out.println(addPrecedingCommasIn(string));
            System.out.println(String.format("dry:\t\t\t%s", string));
//            System.out.println(String.format("sprinkled:\t\t%s", sprinkleCommasIn(string)));
//            System.out.println(String.format("post-sprinkled:\t\t%s", addSucceedingCommasIn1(string)));
            System.out.println(String.format("pre-sprinkled:\t\t%s", addPrecedingCommasIn1(string)));
            System.out.println();
        }
    }

    private static String sprinkleCommasIn(String input) {
        String result = input;
        String prev;

        do {
            prev = result;
            result = addPrecedingCommasIn(addSucceedingCommasIn(result));
        } while(!(result.equals(prev)));

        return result;
    }

    private static String addPrecedingCommasIn1(String input) {
        return addCommasIn(input, "((\\s)|(?=[.,/!;:-]\\s))", " ", character -> token -> token.prefixPunctuation == character);
    }

    private static String addCommasIn(String input, String splitRegex, String joinDelimiter, Function<Character, Predicate<Token>> hasPunctuation) {
        String[] split = input.split(splitRegex);

        Arrays.stream(split).map(s -> String.format("[%s]", s)).forEach(System.out::println);

        List<Token> tokens = Arrays.stream(split)
                .map(Token::new)
                .collect(Collectors.toList());

        tokens.stream().map(t -> t.raw).forEach(System.out::println);

        List<Token> tokensWithCommas = tokens.stream()
                .filter(hasPunctuation.apply(COMMA))
                .collect(Collectors.toList());

        Function<Token, Predicate<Token>> wordMatches = t ->
                token -> t.stripped.equals(token.stripped);

        Predicate<Token> mayNeedComma = t -> tokensWithCommas.stream().anyMatch(wordMatches.apply(t));

        List<Token> result = tokens.stream()
                .map(t1 -> {
                    Optional<Token> matchingToken = tokensWithCommas.stream().filter(wordMatches.apply(t1)).findFirst();

                    if (matchingToken.isPresent() && mayNeedComma.test(t1) && hasPunctuation.apply(NONE).test(t1)) {
                        return matchingToken.get();
                    }

                    return t1;
                })
                .collect(Collectors.toList());

        return result.stream().map(a -> a.raw).collect(Collectors.joining(joinDelimiter));
    }

    private static String addSucceedingCommasIn1(String input) {
        List<Token> tokens = Arrays.stream(input.split(" "))
                .map(Token::new)
                .collect(Collectors.toList());

        Function<Character, Predicate<Token>> hasPunctuation = character ->
                token -> token.suffixPunctuation == character;

        List<Token> tokensWithSucceedingCommas = tokens.stream()
                .filter(hasPunctuation.apply(COMMA))
                .collect(Collectors.toList());

        Function<Token, Predicate<Token>> wordMatches = t ->
                token -> t.stripped.equals(token.stripped);
        Predicate<Token> mayNeedComma = t -> tokensWithSucceedingCommas.stream().anyMatch(wordMatches.apply(t));

        List<Token> result = tokens.stream().map(t1 -> {
                        Optional<Token> matchingToken = tokensWithSucceedingCommas.stream().filter(wordMatches.apply(t1)).findFirst();

                        if (matchingToken.isPresent() && mayNeedComma.test(t1) && hasPunctuation.apply(NONE).test(t1)) {
                            return matchingToken.get();
                        }

                        return t1;
                    })
                .collect(Collectors.toList());

        return result.stream().map(a -> a.raw).collect(Collectors.joining(" "));
    }

    private static class Token {
        String raw;
        String stripped;
        char prefixPunctuation;
        char suffixPunctuation;

        Token(String raw) {
            this.raw = raw;
            this.stripped = raw;
            this.prefixPunctuation = NONE;
            this.suffixPunctuation = NONE;

            boolean hasStartPunctuation = hasStartPunctuation(raw);
            boolean hasEndPunctuation = hasEndPunctuation(raw);

            if (hasStartPunctuation) {
                this.prefixPunctuation = firstOf(raw);
                this.stripped = this.raw.substring(2, this.raw.length());
            } else if (hasEndPunctuation) {
                this.prefixPunctuation = lastOf(raw);
                this.stripped = this.raw.substring(0, this.raw.length() - 1);
            }

            System.out.println(String.format(
                    "raw: [%s]. stripped: [%s]. start punctuation: [%c]. end punctuation: [%c]",
                    this.raw, this.stripped, this.prefixPunctuation, this.suffixPunctuation));
        }

        private static char firstOf(String s) {
            return s.charAt(0);
        }

        private static char lastOf(String s) {
            return s.charAt(s.length() - 1);
        }

        private static boolean hasEndPunctuation(String s) {
            return Pattern.compile("^[\\w]+[.,/!;:-]$")
                    .matcher(s)
                    .matches();
        }

        private static boolean hasStartPunctuation(String s) {
            return Pattern.compile("^[.,/!;:-] [\\w]+$")
                    .matcher(s)
                    .matches();
        }
    }

    private static String addPrecedingCommasIn(String input) {
        String[] tokens = input.split(" ");

        for (int i = 0; i < tokens.length; i++) {
            String token = tokens[i];
            String prevToken = (i == 0) ? " " : tokens[i - 1];

            // look for occurrence of comma preceding word
            if (lastCharOf(prevToken) == COMMA) {
                String searchToken = stripCommaOrPeriodFrom(token);

                for (int j = 0; j < tokens.length; j++) {
                    if (i > 0 && i - 1 == j) continue;

                    if (searchToken.equals(stripCommaOrPeriodFrom(tokens[j]))) {
                        String pTok = (j == 0) ? " " : tokens[j - 1];

                        if (lastCharOf(pTok) != COMMA && lastCharOf(pTok) != PERIOD) {
                            tokens[j - 1] += COMMA;
                        }
                    }
                }
            }
        }

        return String.join(" ", tokens);
    }

    private static String addSucceedingCommasIn(String input) {
        String[] tokens = input.split(" ");

        for (int i = 0; i < tokens.length; i++) {
            String token = tokens[i];

            // look for occurrence of comma succeeding word
            if (lastCharOf(token) == COMMA) {
                String searchToken = stripCommaOrPeriodFrom(token);

                for (int j = 0; j < tokens.length; j++) {
                    if (i == j) continue;

                    String otherToken = stripCommaOrPeriodFrom(tokens[j]);

                    if (searchToken.equals(otherToken) && lastCharOf(tokens[j]) != PERIOD && lastCharOf(tokens[j]) != COMMA) {
                        tokens[j] += COMMA;
                    }
                }
            }
        }
        return String.join(" ", tokens);
    }

    private static String stripCommaOrPeriodFrom(String s) {
        if (lastCharOf(s) == PERIOD || lastCharOf(s) == COMMA) {
            s = s.substring(0, s.length() - 1);
        }

        return s;
    }

    private static char lastCharOf(String str) {
        return str.charAt(str.length() - 1);
    }
}