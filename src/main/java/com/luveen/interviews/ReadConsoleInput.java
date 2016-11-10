package com.luveen.interviews;

import java.util.Scanner;

/**
 * Created by Luveen Wadhwani on 11/5/2016.
 *
 * Sample boilerplate to read a character from console input and act on it.
 */
public class ReadConsoleInput {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final char Y = 'y';
    private static final char N = 'n';

    public static void main(String[] args) {

        while (true) {
            verifyLine();
            verifyYOrN(true);
            verifyNumber(true);
            verifyValidChoice();
            verifyExit();
        }
    }

    private static void verifyLine() {
        System.out.print("Enter a line of text: ");

        String s = SCANNER.nextLine();

        System.out.println(String.format("Text entered:\n%s", s));
    }

    private static char verifyYOrN(boolean shouldShowPrompt) {
        if(shouldShowPrompt) {
            System.out.print("Are you sure? (y/n): ");
        }

        char c = SCANNER.next(".").toLowerCase().charAt(0);
        String outputFormat;

        if (c == Y || c == N) {
            outputFormat = "Valid input %c detected.";
        }
        else {
            outputFormat = "Invalid input %c!";
        }

        System.out.println(String.format(outputFormat, c));

        return c;
    }

    private static Integer verifyNumber(boolean shouldShowPrompt) {
        if (shouldShowPrompt) {
            System.out.print("Enter a number. Any number: ");
        }

        Integer i = null;

        try {
            i = SCANNER.nextInt();

            System.out.println(String.format("%s was entered.", i));
        }
        catch (NumberFormatException nfe) {
            nfe.printStackTrace();
        }

        return i;
    }

    private static void verifyValidChoice() {
        System.out.print("Enter a number between 1 and 10: ");

        Integer n = verifyNumber(false);

        if (n < 1 || n > 10) {
            System.out.println(String.format("Invalid input %s!", n));
        }
    }

    private static void verifyExit() {
        System.out.print("Would you like to exit? (y/n): ");

        char c = verifyYOrN(false);

        switch (c) {
            case Y:
                System.exit(0);

            case N:
                break;
        }
    }
}
