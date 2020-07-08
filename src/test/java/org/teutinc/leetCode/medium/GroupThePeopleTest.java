package org.teutinc.leetCode.medium;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GroupThePeopleTest {
    static boolean equals(List<Integer> l, int[] a) {
        if (l.size() != a.length) {
            return false;
        }
        for (int i = 0; i < a.length; i++) {
            if (l.get(i) != a[i]) {
                return false;
            }
        }
        return true;
    }

    static boolean assertResult(List<List<Integer>> res, int[][]... expectations) {
        for (int[][] expectation : expectations) {
            if (expectation.length == res.size()) {
                for (int i = 0; i < expectation.length; i++) {
                    var expectationItem = expectation[i];
                    var resItem = res.get(i);
                    if (equals(resItem, expectationItem)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Test
    void it_should_validate_example_1() {
        // GIVEN
        var groupSizes = new int[]{3, 3, 3, 3, 3, 1, 3};

        // WHEN
        var res = new GroupThePeople().groupThePeople(groupSizes);

        // THEN
        assertTrue(
                assertResult(
                        res,
                        new int[][]{{5}, {0, 1, 2}, {3, 4, 6}},
                        new int[][]{{2, 1, 6}, {5}, {0, 4, 3}},
                        new int[][]{{5}, {0, 6, 2}, {4, 3, 1}}
                )
        );
    }

    @Test
    void it_should_validate_example_2() {
        // GIVEN
        var groupSizes = new int[]{2, 1, 3, 3, 3, 2};

        // WHEN
        var res = new GroupThePeople().groupThePeople(groupSizes);

        // THEN
        assertResult(
                res,
                new int[][]{{1}, {0, 5}, {2, 3, 4}}
        );
    }
}