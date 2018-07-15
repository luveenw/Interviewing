package com.interviews.luveen;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Sets;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @interview Remix On-Site Coding 1 07/10/2018
 */
public class Table {
    private List<Row> rows;

    public static void main(String[] args) {
        Table table = new Table();

        table.insert(ImmutableMap.of(
                "name", "Fred",
                "breed", "Border Collie",
                "fuzz", 6));
        table.insert(ImmutableMap.of(
                "name", "Fuzzball",
                "breed", "Border Collie",
                "fuzz", 9));
        table.insert(ImmutableMap.of(
                "name", "Clifford",
                "breed", "Big Red",
                "fuzz", 1));

        // test select all
        table.select(new HashMap<>()).forEach(System.out::println);

        // test select with filter
        table.select(ImmutableMap.of("breed", "Border Collie")).forEach(System.out::println);
        table.select(ImmutableMap.of("breed", "Border Collie", "name", "Fred")).forEach(System.out::println);

        // test delete
        System.out.println(String.format("%d rows deleted.", table.delete(ImmutableMap.of(
                "name", "Fred"))));

        table.select(new HashMap<>()).forEach(System.out::println);

    }

    private Table() {
        this.rows = new ArrayList<>();
    }

    public void insert(Map<String, Object> row) {
        this.rows.add(new Row(row));
    }

    public List<Row> select(Map<String, Object> filters) {
        if (filters.isEmpty()) {
            return this.rows;
        }

        List<Row> result = this.rows;

        for (String key : filters.keySet()) {
            Object value = filters.get(key);

            result = result.stream()
                    .filter(row -> row.columnNameToColumns.get(key).equals(value))
                    .collect(Collectors.toList());
        }

        return ImmutableList.copyOf(result);
    }

    public int delete(Map<String, Object> filters) {
        Set<Row> selected = new HashSet<>(select(filters));

        this.rows = new ArrayList<>(Sets.difference(new HashSet<>(this.rows), selected));

        return selected.size();
    }

    public String toString() {
        return this.rows.stream()
                .map(Row::toString)
                .collect(Collectors.joining("\n"));
    }

    private static class Row {
        Map<String, Object> columnNameToColumns;

        Row(Map<String, Object> columns) {
            this.columnNameToColumns = columns;
        }

        @Override
        public String toString() {
            return this.columnNameToColumns.keySet().stream()
                    .map(key ->
                            String.format("%s: %s", key, this.columnNameToColumns.get(key)))
                    .collect(Collectors.joining(", "));
        }
    }
}
