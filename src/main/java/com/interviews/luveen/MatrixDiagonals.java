package com.interviews.luveen;

/**
 * @interview ??? Phone screen 1 5/8/2018
 */

class MatrixDiagonals {
    public static void main(String[] args) {
        int[][] a ={
                {9, 3, 2},
                {8, 6, 1},
                {5, 5, 6},
                {1, 2, 8}};

        int [][] b = {
                {1, 2},
                {3, 4}
        };

        int[][] c = {
                {9, 3, 2, 8},
                {8, 6, 1, 5},
                {5, 6, 1, 2}};

        int[][] d = {
                {1}
        };

        printDiagonalsFor(a);
        printDiagonalsFor(b);
        printDiagonalsFor(c);
        printDiagonalsFor(d);
    }

    public static void printDiagonalsFor(int[][] a) {
        int numRows = a.length;
        int numCols = a[0].length;

        for (int j = 0; j < numCols; j++) {
            StringBuilder output = new StringBuilder();

            for (int k = 0, l = j; k < numRows && l >= 0; k++, l--) {
                output.append(a[k][l]).append(" ");
            }

            System.out.println(output.toString());
        }

        for (int p = 1; p < numRows; p++) {
            StringBuilder output = new StringBuilder();

            for (int n = p, m = numCols - 1; n < numRows && m >= 0; n++, m--) {
                output.append(a[n][m]).append(" ");
            }

            System.out.println(output.toString());
        }
    }
}


/*
Given an MxN matrix, write code which prints out the diagonals (from upper right to lower left) of the matrix.
In this example where M = 4, N = 3:
[[9, 3, 2],
[8, 6, 1],
[5, 5, 6],
[1, 2, 8]]


Your code should print out:
9
3 8
2 6 5
1 5 1
6 2
8
*/