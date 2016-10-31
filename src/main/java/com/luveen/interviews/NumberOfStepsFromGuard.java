package com.luveen.interviews;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Luveen Wadhwani on 10/23/2016.
 *
 */
public class NumberOfStepsFromGuard {
    private static final char GUARD = 'G';
    private static final char OPEN = 'O';
    private static final char WALL = 'W';
    private static final int UNVISITED = Integer.MAX_VALUE;

    private static class ResultCell {
        int x;
        int y;
        int distance;

        ResultCell(int x, int y) {
            this.x = x;
            this.y = y;
            this.distance = 0;
        }

        ResultCell(int x, int y, int distance) {
            this(x, y);
            this.distance = distance;
        }

        @Override
        public String toString() {
            return "(" + this.x + ", " + this.y + "), " + this.distance;
        }
    }

    private static int[][] generateDistanceMap(char[][] groundsMap) {
        int m = groundsMap.length;
        int n = groundsMap[0].length;
        int[][] result = new int[m][n];
        Queue<ResultCell> queue = new LinkedList<>();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                result[i][j] = UNVISITED;

                if (groundsMap[i][j] == GUARD) {
                    queue.add(new ResultCell(i, j));
                }
            }
        }

        while(!queue.isEmpty()) {
            ResultCell c = queue.remove();

            result[c.x][c.y] = Math.min(result[c.x][c.y], c.distance);

            int nextCellDistance = result[c.x][c.y] + 1;

            // LEFT
            addToQueueIfValid(queue, c.x, c.y - 1, m, n, groundsMap, result, nextCellDistance);
            // UP
            addToQueueIfValid(queue, c.x - 1, c.y, m, n, groundsMap, result, nextCellDistance);
            // DOWN
            addToQueueIfValid(queue, c.x + 1, c.y, m, n, groundsMap, result, nextCellDistance);
            // RIGHT
            addToQueueIfValid(queue, c.x, c.y + 1, m, n, groundsMap, result, nextCellDistance);
            // result[c.x][c.y] = c.distance;
        }

        return result;
    }

    private static void addToQueueIfValid(
            Queue<ResultCell> queue, int x, int y, int m, int n, char[][] groundsMap, int[][] result, int dist) {
        if (x < 0 || x >= m || y < 0 || y >= n
                || groundsMap[x][y] == GUARD || groundsMap[x][y] == WALL || result[x][y] != UNVISITED) {
            return;
        }

        ResultCell cell = new ResultCell(x, y, dist);
        queue.add(cell);
    }

    private static void printCharArray(char[][] arr) {
        for (char[] a : arr) {
            for (int j = 0; j < a.length; j++) {
                System.out.print(a[j]);

                if (j < a.length - 1) {
                    System.out.print(' ');
                }
            }

            System.out.println();
        }
    }

    private static void printIntArray(int[][] arr) {
        for (int[] a : arr) {
            for (int j = 0; j < a.length; j++) {
                System.out.print(a[j]);

                if (j < a.length - 1) {
                    System.out.print(' ');
                }
            }

            System.out.println();
        }
    }

    public static void main(String[] args) {
        char[][] map1 = new char[][] {
                {GUARD, OPEN, OPEN, OPEN, GUARD, OPEN, OPEN},
                {OPEN, WALL, OPEN, OPEN, OPEN, OPEN, WALL},
                {OPEN, OPEN, GUARD, WALL, GUARD, OPEN, OPEN},
                {OPEN, OPEN, OPEN, OPEN, OPEN, OPEN, OPEN},
                {OPEN, WALL, OPEN, OPEN, OPEN, OPEN, OPEN}
        };

        printCharArray(map1);
        System.out.println();
        printIntArray(generateDistanceMap(map1));
    }
}
