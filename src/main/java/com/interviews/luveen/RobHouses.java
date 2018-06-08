package com.interviews.luveen;

public class RobHouses {
    private static final int[] A = {25, 100, 79, 55, 149, 34};
    private static final int[] B = {20, 10, 5, 30, 4, 7, 9, 45};

    public static void main(String[] args) {
        System.out.println(String.format("Robbing houses in A yields %d", robHouses(A)));
        System.out.println(String.format("Robbing houses in B yields %d", robHouses(B)));

        System.out.println(String.format("Robbing houses in A recursively yields %d", robHousesRecurse(A, 0)));
        System.out.println(String.format("Robbing houses in B recursively yields %d", robHousesRecurse(B, 0)));
    }

    private static int robHouses(int[] monies) {
        int numMonies = monies.length;

        if (numMonies == 1) {
            return monies[0];
        }

        if (numMonies == 2) {
            return Math.max(monies[0], monies[1]);
        }

        int[] T = new int[numMonies];

        T[0] = monies[0];
        T[1] = Math.max(monies[0], monies[1]);

        for (int i = 2; i < monies.length; i++) {
            T[i] = Math.max(T[i - 1], T[i - 2] + monies[i]);
        }

        return T[numMonies - 1];
    }

    private static int robHousesRecurse(int[] monies, int n) {
        if (n == monies.length - 1 || n == monies.length - 2) {
            return monies[n];
        }

        int amount1 = monies[n] + robHousesRecurse(monies, n + 2);

        if (n + 3 > monies.length - 1) {
            return amount1;
        }

        return Math.max(amount1, monies[n] + robHousesRecurse(monies, n + 3));
    }
}
