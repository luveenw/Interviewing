package com.interviews.luveen;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.SetMultimap;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by Luveen Wadhwani on 2/12/2017.
 *
 * @interview DITTO Initial Technical Assessment Quiz (Offline, Take-home) 02/11/2017
 */
public class CollinearPoints {
    /**
     * <p>Determine which points in the specified input are collinear, and group them together.</p>
     * <p>A line is represented as a {@link Set} of {@link Point}s. The result contains a set of sets, with each set
     * containing collinear {@link Point}s.</p>
     *
     * <p>The algorithm employed uses line slope as a key measure.</p>
     *
     * <h1>Complexity Analysis</h1>
     * <ul>
     *     <li>Run time complexity - <code>O(n^2)</code> (the algorithm used will analyze [n * (n - 1)] / 2 pairs of points)</li>
     *     <li>Space complexity - <code>O(n)</code> (worst-case additional storage for slopes map; every possible line
     *     may have different slopes)</li>
     * </ul>Run time
     *
     * @param points the {@link List} of {@link Point}s to group into lines.
     * @return A {@link Set} containing collinear {@link Point}s as specified above.
     */
    private static Set<Set<Point>> getLinesWithMoreThanTwoPointsIn(List<Point> points) {
        Set<Set<Point>> result = new HashSet<>();
        SetMultimap<Double, Point> slopeToPointsOnLine = HashMultimap.create();

        if (points != null) {
            for (int i = 0; i < points.size(); i++) {
                Point p = points.get(i);

                for (int j = i + 1; j < points.size(); j++) {
                    Point q = points.get(j);

                    slopeToPointsOnLine.put(slopeBetween(p, q), p);
                    slopeToPointsOnLine.put(slopeBetween(p, q), q);
                }
            }

            for (Double slope : slopeToPointsOnLine.keySet()) {
                Set<Point> pointsAtSlope = slopeToPointsOnLine.get(slope);

                if (pointsAtSlope.size() > 2) {
                    result.add(pointsAtSlope);
                }
            }
        }

        return result;
    }

    /**
     * Helper method to calculate the slope between two {@link Point}s.
     *
     * @param a a {@link Point}.
     * @param b another {@link Point}
     * @return The slope of the line going from {@code a} to {@code b}.
     */
    private static double slopeBetween(Point a, Point b) {
        checkNotNull(a);
        checkNotNull(b);

        if (b.x == a.x) {
            if (b.y == a.y) {
                return Double.NEGATIVE_INFINITY;
            }

            return Double.POSITIVE_INFINITY;
        }

        if (b.y == a.y) {
            return 0.0;
        }

        return (double) (b.y - a.y) / (b.x - a.x);
    }

    /**
     * Test cases.
     * @param args
     */
    public static void main(String[] args) {
        // test_example
        printResultOf(getLinesWithMoreThanTwoPointsIn(Lists.newArrayList(
                new Point(0.0, 0.0),
                new Point(1.0, 1.0),
                new Point(3.0, 4.0),
                new Point(2.0, 2.0)
        )));

        // Input with repeated points
        printResultOf(getLinesWithMoreThanTwoPointsIn(Lists.newArrayList(
                new Point(0.0, 0.0),
                new Point(1.0, 1.0),
                new Point(0.0, 0.0),
                new Point(2.0, 2.0)
        )));

        // Empty input
        printResultOf(getLinesWithMoreThanTwoPointsIn(Lists.newArrayList()));

        // Null input
        printResultOf(getLinesWithMoreThanTwoPointsIn(null));

        // Input with no three points being collinear
        printResultOf(getLinesWithMoreThanTwoPointsIn(Lists.newArrayList(
                new Point(1.1, 2.0),
                new Point(3.0, 4.7),
                new Point(5.0, 6.5),
                new Point(7.4, 8.0)
        )));

        // Input with all points being collinear
        printResultOf(getLinesWithMoreThanTwoPointsIn(Lists.newArrayList(
                new Point(1.0, 2.0),
                new Point(2.0, 4.0),
                new Point(3.0, 6.0)
        )));
    }

    /**
     * Helper method for printing results.
     *
     * @param result The result object to print.
     */
    private static void printResultOf(Set<Set<Point>> result) {
        System.out.println(result
                .stream()
                .map(points -> points
                        .stream()
                        .map(Point::toString)
                        .collect(Collectors.joining(", ", "[ ", " ]")))
                .collect(Collectors.joining(", ", "{ ", " }")));
    }

    /**
     * Representation of a point.
     */
    private static class Point {
        final double x;
        final double y;

        Point(double x, double y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return this.x == point.x &&
                    this.y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(this.x, this.y);
        }

        @Override
        public String toString() {
            return String.format("(%.2f, %.2f)", this.x, this.y);
        }
    }
}
