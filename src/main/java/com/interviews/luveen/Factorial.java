package com.interviews.luveen;

import java.math.BigInteger;
import java.util.stream.IntStream;

public class Factorial {
    public static void main(String[] args) {
        IntStream.range(1, 100)
                .mapToObj(n -> String.format("factorial(%d): %d", n, factorial(n)))
                .forEach(System.out::println);
    }

    private static BigInteger factorial(int n) {
        if (n == 0) return BigInteger.valueOf(1);

        return IntStream.range(1, n + 1)
                .mapToObj(BigInteger::valueOf)
                .reduce(BigInteger.valueOf(1), BigInteger::multiply);
    }
}
