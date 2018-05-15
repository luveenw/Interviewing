package com.interviews.luveen;

import com.google.common.collect.Lists;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @interview {Su} Google On-site {date unknown}
 */
public class MakingChange {
    public static void main(String[] args) {
        List<List<Integer>> a = makeChangeFor(8, new int[] {1, 4, 6, 7});
        List<List<Integer>> b = makeChangeFor(6, new int[] {1, 2, 3});

        printListOfLists(a);
        printListOfLists(b);

        printList(minCoinsFor(13, new int[] {7, 2, 3, 6}));
        printList(minCoinsFor(8, new int[] {1, 4, 6, 7}));
    }

    private static void printListOfLists(List<List<Integer>> a) {
        a.forEach(MakingChange::printList);
    }

    private static void printList(Collection list) {
        System.out.println(
                list.stream()
                        .map(Object::toString)
                        .collect(Collectors.joining(", ")));
    }

    static List<Integer> minCoinsFor(int total, int[] denominations) {
        int[] T = new int[total + 1];
        int[] S = new int[total + 1];

        Arrays.fill(T, Integer.MAX_VALUE - 1);
        Arrays.fill(S, -1);
        T[0] = 0;

        for (int j = 0; j < denominations.length; j++) {
            for (int i = 1; i < T.length; i++) {
                if (i < denominations[j]) {
                    continue;
                }

                int temp = 1 + T[i - denominations[j]];

                if (T[i] >= temp) {
                    T[i] = temp;
                    S[i] = j;
                }
            }
        }

        int remaining = total;
        List<Integer> result = Lists.newArrayList();

        while (remaining != 0) {
            int denomination = denominations[S[remaining]];

            result.add(denomination);
            remaining -= denomination;
        }

        return result;
    }

    static List<List<Integer>> makeChangeFor(int total, int[] denominations) {
        List<List<Integer>> result = new ArrayList<>();

        for (int i = 0; i < denominations.length; i++) {
            result.addAll(makeChangeFor(total, denominations, i));
        }

        return result;
    }

    private static List<List<Integer>> makeChangeFor(int total, int[] denominations, int index) {
        int temp = total - denominations[index];

        // If we went down a recursion subtree that totalled too high, abort
        if (temp < 0) {
            return Collections.emptyList();
        }

        // If we went down a recursion subtree that totalled correctly, return the result obtained by subtracting
        // the current denomination from the total
        if (temp == 0) {
            List<List<Integer>> result = Lists.newArrayList();
            result.add(Lists.newArrayList(denominations[index]));

            return result;
        }

        List<List<Integer>> result1 = Lists.newArrayList();

        for (int i = index; i < denominations.length; i++) {
//        for (int i = index + 1; i < denominations.length; i++) {
            result1.addAll(makeChangeFor(temp, denominations, i)
                    .stream()
                    .filter(list -> !list.isEmpty())
                    .map(list -> { list.add(0, denominations[index]); return list; })
                    .collect(Collectors.toList()));
        }

        return result1;
    }
}
