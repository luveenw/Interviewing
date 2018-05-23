package com.interviews.luveen;

/**
 * Given a 2d matrix, count all possible paths from top left to bottom right. The only valid moves are
 * <ol>
 *     <li>One to the right</li>
 *     <li>One down</li>
 * </ol>
 */
public class MatrixCountPaths {
    private static final int[][] A = new int[][] {
            {0, 0, 0},
            {0, 0, 0},
            {0, 0, 0},
    };

    private static final int[][] B = new int[][] {
            {0, 0},
            {0, 0}
    };

    public static void main(String[] args) {
        System.out.println(String.format("Num paths in A: %d", countPathsIn(A, 0, 0)));
        System.out.println(String.format("Num paths in B: %d", countPathsIn(B, 0, 0)));
    }

    private static int countPathsIn(int[][] a, int row, int col) {
        if (row == a.length - 1 && col == a[0].length - 1) {
            return 1;
        }

        int m = 0;
        int n = 0;

        if (row < a.length - 1) {
            m = countPathsIn(a, row + 1, col);
        }

        if (col < a[0].length - 1) {
            n = countPathsIn(a, row, col + 1);
        }

        return m + n;
    }
}
