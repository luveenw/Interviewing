package com.interviews.suiyer;

import javax.sound.midi.SysexMessage;

/**
 * Created by Luveen Wadhwani on 1/29/2017.
 *
 * Find all palindromic substrings in a specified string.
 *
 * @interview ? Interviewing.io Screen 01/27/2017
 */
public class PalindromicSubstrings {

    public static void main(String args[]) {
        PalindromicSubstrings pals = new PalindromicSubstrings();
        System.out.println("aabbaa is " + pals.getPalindromeCount("aabbaa"));
        System.out.println("aabbcc is " + pals.getPalindromeCount("aabbcc"));
        System.out.println("aabaa is " + pals.getPalindromeCount("aabaa"));
        System.out.println("aba is " + pals.getPalindromeCount("aba"));
        System.out.println("aa is " + pals.getPalindromeCount("aa"));
        System.out.println("abcabcdedcbacba is " + pals.getPalindromeCount("abcabcdedcbacba"));
    }

    public int getPalindromeCount(String input) {
        char[] inputChars = input.toCharArray();
        int palindromeCount = 0;
        // For each character check:
        for(int i =0; i<inputChars.length-1;i++) {
            // Odd palindromes with this char as the center.
            palindromeCount+=isPalindrome(inputChars,i-1,i+1);
            // Even palindromes with this char as the center.
            palindromeCount+=isPalindrome(inputChars,i,i+1);

        }
        return palindromeCount;
    }

    public int isPalindrome(char[] input, int i, int j) {
        int palindromeCount = 0;
        if (i < 0 || j > input.length - 1) {
            return palindromeCount;
        }
        // Break if 2 chars are not equal.
        while (input[i] == input[j]) {
            palindromeCount ++;
            i--;
            j++;
            if (i < 0 || j > input.length - 1) {
                break;
            }
        }
        return palindromeCount;
    }
}
