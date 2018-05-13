package com.interviews.luveen;

import java.util.*;

/**
 * @interview Cruise interviewing.io Phone Screen 5/11/2018
 */
public class ConnectedComponents {
/*

input:
[ 1 1 1 1 1 1 ]
[ 1 0 0 0 0 1 ]
[ 1 0 1 1 0 1 ]
[ 1 0 1 1 0 1 ]
[ 1 0 0 0 0 1 ]
[ 1 1 1 1 1 1 ]

starting index: for example, [2, 2]

output:
lists of indices representing the components connected to that location in the grid

for this grid: [[2, 2], [2, 3], [3, 2], [3, 3]]

 */

        public static void main(String[] args) {
            int[][] x = new int[][] {
                    {1,1,1,1,1,1},
                    {1,0,0,0,0,1},
                    {1,0,1,1,0,1},
                    {1,0,1,1,0,1},
                    {1,0,0,0,0,1},
                    {1,1,1,1,1,1}
            };

            Coord start1 = new Coord(2, 2);
            Coord start2 = new Coord(0, 0);
            Coord start3 = new Coord(1, 1);
            Set<Coord> set = new HashSet<>();

            set.add(start1);

//            System.out.println(String.format("start1.equals(start1)? %b", start1.equals(start1)));
//            System.out.println(String.format("start1.equals(start2)? %b", start1.equals(start2)));
//            System.out.println(String.format("start1.equals(start3)? %b", start1.equals(start3)));
//            System.out.println(String.format("set.contains(start1)? %b", set.contains(start1)));
//            System.out.println(String.format("set.contains(start2)? %b", set.contains(start2)));

            getConnectedComponentsFor(x, start1).forEach(c -> System.out.println(String.format("[%d, %d]", c.row, c.col)));
//            getConnectedComponentsFor(x, start2).forEach(c -> System.out.println(String.format("[%d, %d]", c.row, c.col)));
//            getConnectedComponentsFor(x, start3).forEach(c -> System.out.println(String.format("[%d, %d]", c.row, c.col)));
        }

        public static List<Coord> getConnectedComponentsFor(int[][] a, Coord start) {
            ArrayDeque<Coord> que = new ArrayDeque<>();
            List<Coord> result = new ArrayList<>();
            Set<Coord> visited = new HashSet<>();
            int i = 0;

            que.add(start);

            while(!que.isEmpty()) {
                if (++i == 15) {
                    break;
                }

                System.out.println("Queue:");

                for (Coord b : que) {
                    System.out.println(String.format("[%d, %d]", b.row, b.col));
                }

                System.out.println("Result:");
                for (Coord b : result) {
                    System.out.println(String.format("[%d, %d]", b.row, b.col));
                }

                Coord c = que.removeLast();

                if(!isValid(c, a, visited)) {
                    continue;
                }

                visited.add(c);
                result.add(c);

                System.out.println("Visited set:");
                for (Coord n : visited) {
                    System.out.println(String.format("[%d, %d] ", n.row, n.col));
                }

                List<Coord> nexts = new ArrayList<>();

                nexts.add(new Coord(c.row - 1, c.col));  //up
                nexts.add(new Coord(c.row, c.col + 1));  //right
                nexts.add(new Coord(c.row + 1, c.col));  // down
                nexts.add(new Coord(c.row, c.col - 1));  //left

                nexts.stream()
                        .filter(x -> isValid(x, a, visited))
                        .forEach(que::add);
            }

            return result;
        }

        private static boolean isValid(Coord c, int[][] a, Set<Coord> visited) {
            int x = c.row;
            int y = c.col;

            if (visited.contains(c)) {
                return false;
            }

            if (x < 0 || x > a.length) {
                return false;
            }

            if (y < 0 || y > a[0].length) {
                return false;
            }

            if (a[x][y] == 0) {
                return false;
            }

            System.out.println("Visited set in isValid():");
            for (Coord n : visited) {
                System.out.println(String.format("[%d, %d] ", n.row, n.col));
            }

            System.out.println(String.format(
                    "[%d, %d] (%d) is a valid value. Adding to result...", c.row, c.col, a[x][y]));

            return true;
        }

        private static class Coord {
            int row;
            int col;

            Coord(int r, int c) {
                this.row = r;
                this.col = c;
            }

            public boolean equals(Coord o) {
                return Objects.equals(this, o);
            }

            public int hashCode() {
                return Objects.hash(this.row, this.col);
            }
        }

}
