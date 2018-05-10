package com.interviews.luveen;

/**
 * @interview Color Phone Screen 5/10/2018
 */
public class MinHops {
    public static void main (String[] args) {

        System.out.println(findMinHopsFor(new int[] {1,2,5,1,1,0}));
        System.out.println(findMinHopsFor(new int[] {3,4,3,3,1,1,0}));
        System.out.println(findMinHopsFor(new int[] {3,4,1,1,1,1,0}));
    }

    public static int findMinHopsFor(int[] input) {
        int result = 0;

        int i = 0;

        do {
            int maxSoFar = Integer.MIN_VALUE;
            int maxIndex = i + 1;

            if (i + input[i] >= input.length - 1) {
                i = input.length - 1;
            } else {

                for (int j = i + 1; j <= i + input[i] && j < input.length - 1; j++) {
                    int numSpaces = Math.min(input[j], input.length - j - 1);
                    int curMax = j + numSpaces;

                    if (curMax >= maxSoFar) {
                        maxSoFar = curMax;
                        maxIndex = j;
                    }
                }

                i = maxIndex;
            }

            result++;
        } while (i < input.length - 1);

        return result;
    }
}
