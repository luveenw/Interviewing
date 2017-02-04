package com.interviews.luveen;

import java.util.ArrayList;

/**
 * Created by luvee on 2/3/2017.
 *
 * Generate all possible keycode combinations that are four digits long.
 *
 * @interview GoFundMe Phone Screen 2/3/2017
 */
public class GenKeycodes {
        private static final int NUM_DIGITS = 4;

        public static void main(String[] args) {
            ArrayList<String> strings = new ArrayList<String>();
            strings.add("Hello, World!");
            strings.add("Welcome to CoderPad.");
            strings.add("This pad is running Java 8.");

            for (String string : strings) {
                System.out.println(string);
            }

            System.out.println(getAllFourDigitKeycodes());
        }

    private static String getAllFourDigitKeycodes() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 10000; i++) {
            if (i < 10) {
                sb.append("000").append(i);
            }
            else if (i < 100) {
                sb.append("00").append(i);
            }
            else if (i < 1000) {
                sb.append("0").append(i);
            }
            else {
                sb.append(i);
            }
        }

        return sb.toString();
    }
}
