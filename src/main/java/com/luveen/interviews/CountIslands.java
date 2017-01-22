package com.luveen.interviews;

import java.util.*;

/**
 * Created by luvee on 1/20/2017.
 *
 * Count the number of islands in a 2d level represented as an array of 0s (water) and 1s (land).
 * Unity Onsite 1/11/2017
 */
public class CountIslands {
    private static final int[][] LEVEL_1 = new int[][] {
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 1, 0, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 1, 1, 0, 0, 1},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {0, 1, 1, 0, 0, 0, 1, 1, 0, 1},
            {0, 1, 1, 0, 0, 0, 0, 0, 0, 0},
    };

    private static final String ISLAND_COUNT_MESSAGE_FORMAT = "New island added. Current num islands: %d";

    private static final Pair[] DIRECTIONS = {
            new Pair(0, 1),
            new Pair(0, -1),
            new Pair(1, 0),
            new Pair(-1, 0),
    };

    public static void main(String[] args) {
        countIslands(LEVEL_1).forEach(System.out::print);
    }

    private static List<Integer> countIslands(int[][] level) {
        Set<Pair> unvisitedLand = getUnvisitedLandsIn(level);
        List<Integer> result = new ArrayList<>();

//        Arrays.stream(level).forEach((i, j) -> );

        for (int i = 0; i < level.length; i++) {
            for (int j = 0; j < level[0].length; j++) {
                Pair p = new Pair(i, j);

                if (unvisitedLand.contains(p)) {
                    result.add(countIslandsHelper(p, level, unvisitedLand));

                    System.out.println(String.format(ISLAND_COUNT_MESSAGE_FORMAT, result.size()));
                }
            }
        }

        return result;
    }

    private static int countIslandsHelper(Pair p, int[][] level, Set<Pair> unvisitedLand) {
        if (!unvisitedLand.contains(p)) {
            return 0;
        }

        unvisitedLand.remove(p);

        int result = 1;

        for (Pair d : DIRECTIONS) {
            result += countIslandsHelper(new Pair(p.x + d.x, p.y + d.y), level, unvisitedLand);
        }

        return result;
    }

    private static Set<Pair> getUnvisitedLandsIn(int[][] level) {
        Set<Pair> result = new HashSet<>();

        for (int i = 0; i < level.length; i++) {
            for (int j = 0; j < level[0].length; j++) {
                if (level[i][j] == 1) {
                    result.add(new Pair(i, j));
                }
            }
        }

        return result;
    }

    private static class Pair {
        public int x;
        public int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair pair = (Pair) o;
            return x == pair.x &&
                    y == pair.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
}
