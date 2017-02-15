package com.interviews.suiyer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Luveen Wadhwani on 1/29/2017.
 */
public class MergeMeetings {
    static class Meeting {
        int start;
        int end;
        Meeting(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public String toString() {
            return this.start + " to " + this.end;
        }
    }
    public static void main(String args[]) {
        MergeMeetings mm = new MergeMeetings();
        List<Meeting> allMeetings = new ArrayList<Meeting>();
        allMeetings.add(new Meeting(1,3));
        allMeetings.add(new Meeting(3,4));
        allMeetings.add(new Meeting(4,5));
        allMeetings.add(new Meeting(6,7));
        allMeetings.add(new Meeting(8,9));
        allMeetings.add(new Meeting(9,11));
        allMeetings.add(new Meeting(12,13));

        List<Meeting> mergedMeetings = mm.mergeMeetings(allMeetings);
        System.out.println(mergedMeetings.toString());
    }

    public List<Meeting> mergeMeetings(List<Meeting> meetings) {
        Collections.sort(meetings, new Comparator<Meeting>() {
            public int compare(Meeting m1, Meeting m2) {
                return m1.start==m2.start?0:m1.start<m2.start?-1:1;
            }
        });
        List<Meeting> mergedMeetings = new ArrayList<>();
        Meeting mergedMeeting = meetings.get(0);
        mergedMeetings.add(mergedMeeting);
        for (Meeting meeting : meetings.subList(1,meetings.size())) {
            if(meeting.start <= mergedMeeting.end) {
                mergedMeeting.end = meeting.end;
            } else {
                mergedMeetings.add(meeting);
                mergedMeeting = meeting;
            }
        }

        return mergedMeetings;
    }
}
