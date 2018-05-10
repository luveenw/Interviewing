package com.interviews.luveen;

import com.google.common.collect.Lists;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;

public class MergeConflictingMeetings {
    private static class Meeting {
        int start;
        int end;
        String desc;
        String title;

        public Meeting(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static void main(String[] args) {
        List<Meeting> meetings = Lists.newArrayList(
                new Meeting(1, 2),
                new Meeting(3, 4),
                new Meeting(3, 7),
                new Meeting(4, 5),
                new Meeting(6, 7),
                new Meeting(8, 10)
        );

        Consumer<Meeting> printAction = m -> System.out.println(String.format("(%d, %d)", m.start, m.end));

        meetings.forEach(printAction);

        mergeConflictsIn(meetings)
                .forEach(printAction);
    }

    private static Collection<Meeting> mergeConflictsIn(List<Meeting> meetings) {
        ArrayDeque<Meeting> result = new ArrayDeque<>();

        result.offerLast(meetings.get(0));

        for (Meeting m : meetings.subList(1, meetings.size())) {
            Meeting temp = result.peekLast();

            if (temp != null && temp.end > m.start) {
                temp.start = Math.min(m.start, temp.start);
                temp.end = Math.max(m.end, temp.end);
            } else {
                result.offerLast(m);
            }
        }

        return result;
    }
}
