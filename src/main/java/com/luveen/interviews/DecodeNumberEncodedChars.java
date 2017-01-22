package com.luveen.interviews;

import java.util.List;

/**
 * Created by Luveen Wadhwani on 10/25/2016.
 */
public class DecodeNumberEncodedChars {
    static int getNumDecodes(String encoded) {
        return getNumDecodes(encoded, 0);
    }

    private static int getNumDecodes(String encoded, int index) {
        if (index >= encoded.length()) {
            return 1;
        }

        char c = encoded.charAt(index);

        if (c <= '0' || c > '9') {
            return 0;
        }

        if (index + 1 >= encoded.length()) {
            return 1;
        }

        if (encoded.charAt(index) == '0') {
            return getNumDecodes(encoded, index + 2);
        }

        if (encoded.charAt(index) > '2' || (encoded.charAt(index) == '2' && encoded.charAt(index + 1) > '6')) {
            return getNumDecodes(encoded, index + 1);
        }

        return getNumDecodes(encoded, index + 1) + getNumDecodes(encoded, index + 2);
    }

    public static void main(String[] args) {
        System.out.println(String.format("Number of combinations for 1123: %d", getNumDecodes("1123")));
        System.out.println(String.format("Number of combinations for 127834: %d", getNumDecodes("127834")));
    }
}
