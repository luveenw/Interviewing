package com.luveen.interviews;

/**
 * Created by Luveen Wadhwani on 10/22/2016.
 *
 * Convert a number to its English representation. Uses the western representation of groups of three, separated by
 * commas.
 *
 * Example
 * Input: 5411234
 * Output: five million four hundred eleven thousand two hundred thirty four
 */
public class NumberToEnglish {
    private static final String[] SUFFIXES = new String[] {"", "thousand", "million", "billion", "trillion",
            "quadrillion", "quintillion"};
    private static final String[] TILL_TWENTY = {"", "one", "two", "three", "four", "five", "six", "seven", "eight",
            "nine", "ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen",
            "nineteen"};
    private static final String[] TENS = {"", "", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty",
            "ninety"};
    private static final String HUNDRED = "hundred";
    private static final String ZERO = "zero";
    private static final String NEGATIVE = "negative";
    private static final char SPACE = ' ';

    public static String convert(long num) {
        if (num == 0) {
            return ZERO;
        }

        boolean isNegative = false;

        if (num < 0) {
            isNegative = true;
            num = -num;
        }

        long cur = num;
        int rem = 0;
        int place = 0;
        StringBuilder result = new StringBuilder();

        do {
            rem = (int) (cur % 1000);
            result.insert(0, convertChunk(rem) + SPACE + SUFFIXES[place] + SPACE);
            cur = cur / 1000;
            place++;
        } while (cur > 0);

        if (isNegative) {
            result.insert(0, NEGATIVE + SPACE);
        }

        return result.toString();
    }

    private static String convertChunk(int chunk) {
        StringBuilder result = new StringBuilder();
        int cur = chunk;
        int rem = 0;

        if (cur % 100 < 20) {
            result.append(TILL_TWENTY[(int) (cur % 100)]);
            cur /= 100;
        }
        else {
            rem = cur % 10;
            result.append(TILL_TWENTY[rem]);
            cur /= 10;

            rem = cur % 10;
            result.insert(0, TENS[rem] + SPACE);
            cur /= 10;
        }

        if (cur != 0) {
            result.insert(0, TILL_TWENTY[cur] + SPACE + HUNDRED + SPACE);
        }

        return result.toString();
    }

    public static void main(String[] args) {
        System.out.println(String.format("Text equivalent of 55: %s", convertChunk(55)));
        System.out.println(String.format("Text equivalent of 155: %s", convertChunk(155)));

        System.out.println(String.format("Text equivalent of 382039495573155L: %s", convert(382039495573155L)));
        System.out.println(String.format("Text equivalent of 382039495573150L: %s", convert(382039495573150L)));
        System.out.println(String.format("Text equivalent of 382039495519015L: %s", convert(382039495519015L)));
        System.out.println(String.format("Text equivalent of 382039495519666L: %s", convert(382039495519666L)));
        System.out.println(String.format("Text equivalent of -382039495519666L: %s", convert(-382039495519666L)));
        System.out.println(String.format("Text equivalent of -0: %s", convert(-0)));
    }
}
