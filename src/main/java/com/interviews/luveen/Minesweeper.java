package com.interviews.luveen;

import java.util.Iterator;
import java.util.Random;
import java.util.stream.IntStream;

/**
 * @interview Remix Phone Screen 6/5/2018
 */
public class Minesweeper {
    private static final int[][] MOVES = new int[][] {
            {-1, 0},  // up
            {1, 0},  // down
            {0, -1},  // left
            {0, 1},  // right
            {-1, -1},  // up + left
            {1, -1},  //  down + left
            {-1, 1},  // up + right
            {1, 1},  // down + right
    };

    private static int[][] generateBoard(int width, int height, int numMines) {
        int[][] board = new int[height][width];

        addMinesTo(board, numMines);

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (board[i][j] == -1) {
                    for (int[] move : MOVES) {
                        int row = move[0] + i;
                        int col = move[1] + j;

                        if (isValid(board, row, col)) {
                            board[row][col]++;
                        }
                    }
                }
            }
        }

        return board;
    }

    private static void addMinesTo(int[][] board, int numMines) {
        int numRows = board.length;
        int numCols = board[0].length;

        IntStream rowStream = new Random().ints(0, numRows);
        IntStream colStream = new Random().ints(0, numCols);

        Iterator<Integer> rowStreamIt = rowStream.iterator();
        Iterator<Integer> colStreamIt = colStream.iterator();

        for (int i = 0; i < numMines;) {
            Integer nextRow = rowStreamIt.next();
            Integer nextCol = colStreamIt.next();

            if (board[nextRow][nextCol] != -1) {
                board[nextRow][nextCol] = -1;
                i++;
            }
        }
    }

    private static boolean isValid(int[][] board, int row, int col) {
        int numRows = board.length;
        int numCols = board[0].length;

        return row >= 0 && row < numRows && col >= 0 && col < numCols && board[row][col] != -1;
    }

    public static void main(String[] args) {
//        printBoardFor(generateBoard(7, 4, 4));
        for (int i = 1; i <= 10; i++) {
            printBoardFor(generateBoard(3, 3, 4));
            System.out.println();
        }
//        printBoardFor(generateBoard(7, 4, 4));
    }

    private static void printBoardFor(int[][] board) {
        int numRows = board.length;
        int numCols = board[0].length;

        for (int i = 0; i < numRows; i++) {
            StringBuilder sb = new StringBuilder();

            for (int j = 0; j < numCols; j++) {
                sb.append(board[i][j] == -1 ? "*" : String.valueOf(board[i][j]));
            }

            System.out.println(sb.toString());
        }
    }
}
