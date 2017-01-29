package com.interviews.luveen;

/**
 * Created by Luveen Wadhwani on 10/25/2016.
 */
public class DecodeNumberEncodedChars {
    private static int getNumDecodes(String encoded) {
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

/*
Facebook interview 2016/11/1 actual solution - with edge cases addressed during interview conversation

// Welcome to Facebook!

// This is just a simple shared plaintext pad, with no execution capabilities.

// When you know what language you'd like to use for your interview,
// simply choose it from the dropdown in the top bar.

// Enjoy your interview!

1 => 'A'
2 => 'B'
3 => 'C'
...
26 => 'Z'

'1' => 'A' => 1
'11' => 'AA', 'K' => 2
'111' => 'AAA', 'AK', 'KA' => 3




public class Solution {
  // private static Map<Integer, String> CHARACTER_MAP = createMap();


  public int findNumCombinations(String numberStr) {
    if (numberStr != null && numberStr.charAt(0) == '0') {
      return 0;
    }

    return getNumCombinations(numberStr, 0);
  }

  private int getNumbCombinations(String str, int index) {
    if (index >= str.length()) {
      return 1;
    }

    char c = str.charAt(index);

    if ( c < '0' || c > '9') {
      return 0;
    }

    if ( c == '0') {
      return getNumCombinations(str, index + 2);
    }

    if ( c > '2' || (index < str.length() - 1 && c == '2' && str.charAt(index + 1) > '6')) {
      return getNumCombinations(str, index + 1);
    }

    if (index + 1 == str.length()
    return getNumCombinations(str, index + 1) + getNumCombinations(str, index + 2);
  }

  public static void main(String[] args) {
    int result = new Solution().findNumCombinations("1123");

    // 1, 1, 2, 3
    // 11, 2, 3
    // 1, 12, 3
    // 1, 1, 23
    // 11, 23
  }
}
 */