package com.interviews.luveen;

import java.util.StringTokenizer;

/**
 * Created by luvee on 1/18/2017.
 *
 * Find the smallest number of words between two specified words in a string.
 * @interview PlayStation PS Store Phone 2 1/18/2017
 */
public class SmallestNumWordsBetween {
    public static void main(String[] args) {
        System.out.println(findSmallestWindow("The quick red car jumped car over the Grand quick Canyon. Car quick", "quick", "car"));
    }

    private static int findSmallestWindow(String data, String a, String b) {
        int ap = -1;
        int bp = -1;
        int c = 0;
        int result = Integer.MAX_VALUE;

        for (StringTokenizer tokens = new StringTokenizer(data, " "); tokens.hasMoreTokens(); c++) {
            String token = tokens.nextToken();

            if (token.equalsIgnoreCase(a)) {
                ap = c;
            }

            if (token.equalsIgnoreCase(b)) {
                bp = c;
            }

            if (ap != -1 && bp != -1) {
                result = Math.min(result, Math.abs(ap - bp) - 1);
            }
        }

        return result;
    }
}

// file on disk
// unknown size
//



// The (quick) {red} (car) jumped over the Grand Canyon
// quick, car
// 1

/*
    int shortestDistanceBetweenWordsIn(String a, String b, BufferedReader br) {
        // current window being processed
        int numWords = 0;
        // smallest window found so far
        int lowestNumWords = 0;
        // which word have we found - a, or b?
        String wordFound;

        while (br.hasNext()) {
            String line = br.nextLine();
            StringTokenizer tokens = new StringTokenizer(line, " ");

            while (tokens.hasNext()) {
                String token = tokens.next();


                if (a.equals(token) && wordFound.equals(a)) {
                    numWords = 0;
                }
                else if (b.equals(token) && wordFound.equals(b)) {
                    numWords = 0;
                }

                if (a.equals(token) && wordFound.equals(b)) {
                    lowestNumWords = process(a, a, b, lowestNumWords, numWords);
                    numWords = 0;
                }
                else if (b.equals(token) && wordFound.equals(a)) {
                    lowestNumWords = process(b, b, a, lowestNumWords, numWords);
                    numWords = 0;
                }
                else {
                    numWords++;
                }

            }
        }

        return lowestNumWords;
    }

    private int process(String found, String word1, String word2, int lowest, int cur) {
        int result = lowest;

        if (found.equals(word1)) {
            result = 0;
        }
        else if (found.equals(word2)) {
            if (lowest > 0) {
                lowest = Math.min(lowest, cur);
            }
        }

        return result;
    }*/
