package com.luveen.interviews;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

/**
 * Various maze generation techniques.
 */
public class MazeGeneration {
    public void randomizedDfs(int numRows, int numCols) {
        boolean[][] visited = new boolean[numRows][numCols];
        boolean[][][] maze = new boolean [numRows][numCols][4];

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                visited[i][j] = false;

                for (int k = 0; k < 4; k++) {
                    maze[i][j][k] = false;
                }
            }
        }

        Stack<Pair> history = new Stack<>();
        int curRow = 0;
        int curCol = 0;

        history.add(new Pair(curRow, curCol));

        while (history.size() > 0) {
            visited[curRow][curCol] = true;

            List<Direction> moves = getPossibleMoves(visited, curRow, curCol, numRows, numCols);

            if (moves.size() == 0) {
                Pair popped = history.pop();

                curRow = popped.x;
                curCol = popped.y;
            }
            else {
                history.add(new Pair(curRow, curCol));

                Direction chosen = moves.get(new Random().nextInt(moves.size()));

                switch (chosen) {
                    case LEFT:
                        maze[curRow][curCol][0] = true;
                        curCol--;
                        maze[curRow][curCol][2] = true;
                        break;

                    case UP:
                        maze[curRow][curCol][1] = true;
                        curRow--;
                        maze[curRow][curCol][3] = true;
                        break;

                    case RIGHT:
                        maze[curRow][curCol][2] = true;
                        curCol++;
                        maze[curRow][curCol][0] = true;
                        break;

                    case DOWN:
                        maze[curRow][curCol][3] = true;
                        curRow++;
                        maze[curRow][curCol][1] = true;
                        break;
                }
            }
        }

        maze[0][0][0] = true;
        maze[numRows - 1][numCols - 1][2] = true;
    }

    private List<Direction> getPossibleMoves(boolean[][] visited, int curRow, int curCol, int numRows, int numCols) {
        List<Direction> result = new ArrayList<>();

        if (curCol > 0 && !visited[curRow][curCol - 1]) {
            result.add(Direction.LEFT);
        }

        if (curRow > 0 && !visited[curRow - 1][curCol]) {
            result.add(Direction.UP);
        }

        if (curCol < numCols - 1 && !visited[curRow][curCol + 1]) {
            result.add(Direction.RIGHT);
        }

        if (curRow < numRows - 1 && !visited[curRow + 1][curCol]) {
            result.add(Direction.DOWN);
        }

        return result;
    }

    private class Pair {
        int x;
        int y;

        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private enum Direction {
        LEFT, UP, RIGHT, DOWN
    }
}
