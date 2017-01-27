package com.luveen.interviews;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by luvee on 1/27/2017.
 *
 * Evaluate a specified Futoshiki puzzle solution for correctness.
 * @see <a href="http://www.futoshiki.org">About Futoshiki puzzles</a>
 *
 * @interview Udacity Onsite 01/27/2017
 */
public class FutoshikiPuzzleValidation {
    // class Board {
//   int size;
//   int[][] cells;
//   List<Inequality> inequalities;
// }

    class Inequality {
        Cell cell1;
        Cell cell2;
        InequalityType type;
    }

    class Cell {
        int row;
        int col;
    }

    enum InequalityType {
        LESS_THAN,
        GREATER_THAN
    }

// 1. are cell values in expected range
// 2. does each row contain a value exactly once
// 3. does each column contain value exactly once
// 4. is every inequality satisfied
// 5. is every start state value present

    boolean isValid(int[][] start, int[][] end, List<Inequality> inequalities) {

        boolean result = checkStartStateValuesPresent(start, end);

        // assuming start is valid; check range of values in end
        result &= checkValueRange(end);

        result &= checkValueUniqueness(end);

        result &= checkInequalities(end, inequalities);

        return result;
    }

    private boolean checkStartStateValuesPresent(int[][] start, int[][] end) {
        for (int i = 0; i < start.length; i++) {
            for (int j = 0; j < start[0].length; j++) {
                if (start[i][j] != 0 && start[i][j] != end[i][j]) {
                    return false;
                }
            }
        }

        return true;
    }

    private boolean checkValueRange(int[][] end) {
        return true;
    }

    private boolean checkValueUniqueness(int[][] end) {
        for (int[] anEnd : end) {
            Set<Integer> valuesSoFar = new HashSet<>();

            for (int c : anEnd) {
                if (valuesSoFar.contains(c)) {
                    return false;
                } else {
                    valuesSoFar.add(c);
                }
            }
        }

        for (int i = 0; i < end.length; i++) {
            Set<Integer> valuesSoFar = new HashSet<>();

            for (int j = 0; j < end[0].length; j++) {
                if (valuesSoFar.contains(end[j][i])) {
                    return false;
                }
                else {
                    valuesSoFar.add(end[j][i]);
                }
            }
        }

        return true;
    }

    private boolean checkInequalities(int[][] end, List<Inequality> inequalities) {
        for (Inequality i : inequalities) {
            Cell c1 = i.cell1;
            Cell c2 = i.cell2;
            int a = end[c1.row][c1.col];
            int b = end[c2.row][c2.col];

            switch(i.type) {
                case LESS_THAN:
                    if (a >= b) {
                        return false;
                    }
                    break;

                case GREATER_THAN:
                    if (a <= b) {
                        return false;
                    }
                    break;
            }
        }

        return true;
    }
}
