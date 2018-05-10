package com.interviews.luveen;

public class SudokuChecker {
    public static void main(String[] args) {
        char[][] grid = {
                {'.', '.', '.', '1', '4', '.', '.', '2', '.'},
                {'.', '.', '6', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '1', '.', '.', '.', '.', '.', '.'},
                {'.', '6', '7', '.', '.', '.', '.', '.', '9'},
                {'.', '.', '.', '.', '.', '.', '8', '1', '.'},
                {'.', '3', '.', '.', '.', '.', '.', '.', '6'},
                {'.', '.', '.', '.', '.', '7', '.', '.', '.'},
                {'.', '.', '.', '5', '.', '.', '.', '7', '.'}
        };

        char[][] grid1 = {
                {'.', '.', '.', '.', '2', '.', '.', '9', '.'},
                {'.', '.', '.', '.', '6', '.', '.', '.', '.'},
                {'7', '1', '.', '.', '7', '5', '.', '.', '.'},
                {'.', '7', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '8', '3', '.', '.', '.'},
                {'.', '.', '8', '.', '.', '7', '.', '6', '.'},
                {'.', '.', '.', '.', '.', '2', '.', '.', '.'},
                {'.', '1', '.', '2', '.', '.', '.', '.', '.'},
                {'.', '2', '.', '.', '3', '.', '.', '.', '.'}
        };

        System.out.println("grid is a valid grid? : " + checkIsValidSudoku(grid));
        System.out.println("grid1 is a valid grid? : " + checkIsValidSudoku(grid1));
    }

    public static boolean checkIsValidSudoku(char[][] grid) {
        if (!hasValidRows(grid)) return false;
        if (!hasValidColumns(grid)) return false;

        for (int i = 0; i <= 6; i += 3) {
            for (int j = 0; j <= 6; j += 3) {
                System.out.println(String.format("(%d, %d) ", i, j));
                if (!hasValidGrid(grid, i, j, i + 2, j + 2)) return false;
            }
        }

        return true;
    }

    private static boolean hasValidRows(char[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            System.out.println(String.format("Row (%d) ", i));
            if (!hasValidGrid(grid, i, 0, i, grid.length - 1)) return false;
        }

        return true;
    }

    private static boolean hasValidColumns(char[][] grid) {
        for (int j = 0; j < grid.length; j++) {
            System.out.println(String.format("Column (%d) ", j));
            if (!hasValidGrid(grid, 0, j, grid.length - 1, j)) return false;
        }

        return true;
    }

    private static boolean hasValidGrid(char[][] grid, int startRow, int startCol, int endRow, int endCol) {
        int[] numOccurrences = new int[10];

        for (int i = startRow; i <= endRow; i++) {
            for (int j = startCol; j < endCol; j++) {
                char c = grid[i][j];
                if (isValidChar(c)) {
                    if (++numOccurrences[c - '0'] > 1) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    private static boolean isValidChar(char c) {
        return c > '0' && c <= '9';
    }
}
