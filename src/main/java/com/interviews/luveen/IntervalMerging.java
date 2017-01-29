package com.interviews.luveen;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a collection of intervals, merge a new interval into the collection, merging it with any overlapping intervals
 * in the list.
 *
 */
public class IntervalMerging {
    private static final Interval ONE_THREE = new Interval(1, 3);
    private static final Interval FOUR_SEVEN = new Interval(4, 7);
    private static final Interval EIGHT_TEN = new Interval(8, 10);
    private static final Interval FIFTEEN_SEVENTEEN = new Interval(15, 17);
    private static final Interval EIGHTEEN_TWENTY = new Interval(18, 20);

    private static final List<Interval> INTERVALS = new ArrayList<>(Arrays.asList(
            ONE_THREE, FOUR_SEVEN, EIGHT_TEN, FIFTEEN_SEVENTEEN, EIGHTEEN_TWENTY));

    private static final Interval FIVE_ELEVEN = new Interval(5, 11);
    private static final Interval SEVEN_NINETEEN = new Interval(7, 19);

    public static void main(String[] args) {
        System.out.println(String.format("Merge (%s): %s", FIVE_ELEVEN, printIntervals(merge(INTERVALS, FIVE_ELEVEN))));
        System.out.println(String.format("Merge (%s): %s", SEVEN_NINETEEN, printIntervals(merge(INTERVALS, SEVEN_NINETEEN))));
    }

    private static String printIntervals(List<Interval> intervals) {
        StringBuilder sb = new StringBuilder();

        intervals.forEach((i) -> sb.append(i.toString()).append(' '));

        return sb.toString();
    }

    private static List<Interval> merge(List<Interval> intervals, Interval toMerge)
    {
        List<Interval> result = new ArrayList<>();
        Interval mergedInterval = new Interval(toMerge);

        for (Interval i : intervals) {
            if (i.overlaps(mergedInterval)) {
                mergedInterval.mergeWith(i);
            }
            else {
                result.add(i);
            }
        }

        result.add(mergedInterval);

        return result;
    }

    private static class Interval {
        int low;
        int high;

        Interval(Interval that) {
            this.low = that.low;
            this.high = that.high;
        }

        Interval(int low, int high) {
            this.low = low;
            this.high = high;
        }

        boolean overlaps(Interval that) {
            return this.low <= that.high && this.high >= that.low;
        }

        void mergeWith(Interval that) {
            this.low = Math.min(this.low, that.low);
            this.high = Math.max(this.high, that.high);
        }

        @Override
        public String toString() {
            return String.format("[%d, %d]", this.low, this.high);
        }
    }
}
