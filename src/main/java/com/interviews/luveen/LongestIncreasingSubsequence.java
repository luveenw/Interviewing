package com.interviews.luveen;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LongestIncreasingSubsequence {
    public static void main(String[] args) {
        List<Integer> example1 = Lists.newArrayList(3, 4, -1, 5, 8, 2, 3, 12, 7, 9, 10);
        List<Integer> example2 = Lists.newArrayList(2, 3, 4, 5, 1);
//        List<Integer> lisQuad = findLISQuadraticIn(example1);
//        List<Integer> lisLog = findLISLogIn(example1);
        List<Integer> lisLog2 = findLISLogIn(example2);

        /*System.out.println(String.format(
                "Quadratic time algorithm LIS (example1): %s",
                lisQuad.stream().map(Object::toString).collect(Collectors.joining(", "))));

        System.out.println(String.format(
                "O(nlogn) time algorithm LIS (example1): %s",
                lisLog.stream().map(Object::toString).collect(Collectors.joining(", "))));*/

        System.out.println(String.format(
                "O(nlogn) time algorithm LIS (example2): %s",
                lisLog2.stream().map(Object::toString).collect(Collectors.joining(", "))));
    }

    public static List<Integer> findLISQuadraticIn(List<Integer> a) {
        int size = a.size();
        List<List<Integer>> L = new ArrayList<>();

        // init an empty LIS for each index we will be analyzing
        for (int i = 0; i < a.size(); i++) {
            L.add(i, new ArrayList<>());
        }

        // the LIS of a ending at index 0 consists of the element
        // in a at index 0
        L.get(0).add(a.get(0));

        for (int i = 1; i < size; i++) {
            int num = a.get(i);

            for (int j = 0; j < i; j++) {
                if (a.get(j) < num && L.get(i).size() < L.get(j).size() + 1) {
                    L.set(i, new ArrayList<>(L.get(j)));
                }
            }

            L.get(i).add(num);
        }

        return L.get(size - 1);
    }

    private static int findCeilIndex(List<Integer> a, int[] tail, int l, int r, int key) {
        int start = l;
        int end = r;
        int mid = -1;

        while (start <= end) {
            mid = (end + start) / 2;

            if (mid < r && a.get(tail[mid]) < key && a.get(tail[mid + 1]) >= key) {
                mid++;
                break;
            } else if (a.get(tail[mid]) < key) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        return mid;
    }

    /**
     * Patience sorting algorithm. For each number, if it is
     * 1. bigger than the last element of the LIS so far, add it to the end of the LIS
     * 2. smaller than the first element of the LIS so far, set it as the start of the LIS
     * 3. in between, binary search for the LIS index where it fits correctly between two numbers so the LIS condition
     * is maintained, and set that index in the LIS as this number
     *
     * Maintain 2 arrays:
     * 1. for each of its indices, tail holds the corresponding array index of the last element in the LIS ending at that index
     * 2. for each of its indices, previous holds the corresponding array index for the preceding element of the LIS
     */
    public static List<Integer> findLISLogIn(List<Integer> a) {
        int size = a.size();
        int[] tail = new int[size];
        int[] previous = new int[size];
        int resultLength = 0;

        tail[0] = 0;

        for (int i = 0; i < size; i++) {
            previous[i] = -1;
        }

        for (int i = 1; i < size; i++) {
            int num = a.get(i);
            if (num > a.get(tail[resultLength])) {
                tail[++resultLength] = i;
                previous[tail[resultLength]] = tail[resultLength - 1];
            } else if (num < a.get(tail[0])) {
                tail[0] = i;
            } else {
                int index = findCeilIndex(a, tail, 0, resultLength, num);
                Preconditions.checkArgument(index != -1);
                tail[index] = i;
                previous[tail[index]] = tail[index - 1];
            }
        }

        List<Integer> result = new ArrayList<>();
        int index = tail[resultLength];

        while (index != -1) {
            result.add(0, a.get(index));
            index = previous[index];
        }

        return result;
    }


}
