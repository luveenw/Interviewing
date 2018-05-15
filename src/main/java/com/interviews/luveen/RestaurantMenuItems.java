package com.interviews.luveen;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class RestaurantMenuItems {
    public static void main(String[] args) {
//        printListOfLists(findItemsWithValue1(new int[] {1, 2, 3, 4, 5}, 10, 0));
        printListOfLists(findItemsWithValue1(new int[] {1, 2, 3, 4, 5}, 0, 0));
//        findItemsWithValue(new int[] {1, 2, 3, 5}, 10, 0, Lists.newArrayList());
    }

    private static void printListOfLists(List<List<Integer>> a) {
        for (Collection list : a) {
            System.out.println(
                    list.stream()
                            .map(Object::toString)
                            .collect(Collectors.joining(", ")));
        }
    }

    static void findItemsWithValue(int[] a, int t, int i, List<Integer> r) {
        if (t == 0) {
            System.out.println(r.stream().map(Object::toString).collect(Collectors.joining(", ")));
            return;
        }

        if (i == a.length) {
            return;
        }

        r.add(a[i]);
        findItemsWithValue(a, t - a[i], i + 1, r);
        r.remove(r.size() - 1);
        findItemsWithValue(a, t, i + 1, r);
    }

    static List<List<Integer>> findItemsWithValue1(int[] items, int total, int index) {
        if (index >= items.length || total < 0) {
            return Collections.emptyList();
        }

        int temp = total - items[index];

        if (temp == 0) {
            List<List<Integer>> result = new ArrayList<>();

            result.add(Lists.newArrayList(items[index]));

            return result;
        }

        List<List<Integer>> withItem = findItemsWithValue1(items, temp, index + 1);
        List<List<Integer>> withoutItem = findItemsWithValue1(items, total, index + 1);
        List<List<Integer>> result = Lists.newArrayList();

        result.addAll(withItem
                .stream()
                .filter(list -> !list.isEmpty())
                .map(list -> { list.add(0, items[index]); return list; })
                .collect(Collectors.toList()));

        result.addAll(withoutItem);

        return result;
    }
}
