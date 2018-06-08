package com.interviews.luveen;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RotateArray {
    // Complete the rotLeft function below.
    static int[] rotLeft(int[] a, int d) {
        int len = a.length;

        if (d < 0) {
            d = len + d;
        }

        if (d > len) {
            d %= len;
        }

        if (d == len) {
            return Arrays.copyOf(a, len);
        }

        int[] result = new int[len];
        int j = 0;

        System.out.println(String.format(
                "a: %s, d: %d",
                IntStream.of(a).mapToObj(Integer::toString).collect(Collectors.joining(", ")), d));

        for (int i = d; i < len; i++) {
            System.out.println(String.format("Adding %d from position %d in a at position %d in r...", a[i], i, j));
            result[j++] = a[i];
        }

        for (int i = 0; i < d; i++) {
            System.out.println(String.format("Adding %d from position %d in a at position %d in r...", a[i], i, j));
            result[j++] = a[i];
        }

        return result;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        System.out.println(
                IntStream.of(rotLeft(new int[] {1, 2, 3, 4, 5}, 4))
                        .mapToObj(Integer::toString)
                        .collect(Collectors.joining(", ")));
    }
}
