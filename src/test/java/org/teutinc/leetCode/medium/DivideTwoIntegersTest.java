package org.teutinc.leetCode.medium;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DivideTwoIntegersTest {
    @Test
    void it_should_validate_example_1() {
        // GIVEN
        var dividend = 10;
        var divisor = 3;

        // WHEN
        var res = new DivideTwoIntegers().divide(dividend, divisor);

        // THEN
        assertEquals(
                3,
                res
        );
    }

    @Test
    void it_should_validate_example_2() {
        // GIVEN
        var dividend = 7;
        var divisor = -3;

        // WHEN
        var res = new DivideTwoIntegers().divide(dividend, divisor);

        // THEN
        assertEquals(
                -2,
                res
        );
    }

    @Test
    void it_should_work_with_2_negatives_values() {
        // GIVEN
        var dividend = -7;
        var divisor = -3;

        // WHEN
        var res = new DivideTwoIntegers().divide(dividend, divisor);

        // THEN
        assertEquals(
                2,
                res
        );
    }

    @Test
    void it_should_divide_by_2() {
        // GIVEN
        var dividend = 12;
        var divisor = 2;

        // WHEN
        var res = new DivideTwoIntegers().divide(dividend, divisor);

        // THEN
        assertEquals(
                6,
                res
        );
    }

    @Test
    void it_should_divide_by_2_and_truncate() {
        // GIVEN
        var dividend = 13;
        var divisor = 2;

        // WHEN
        var res = new DivideTwoIntegers().divide(dividend, divisor);

        // THEN
        assertEquals(
                6,
                res
        );
    }

    @Test
    void it_should_validate_negative_max() {
        // GIVEN
        var dividend = -2147483648;
        var divisor = -1;

        // WHEN
        var res = new DivideTwoIntegers().divide(dividend, divisor);

        // THEN
        assertEquals(
                2147483647,
                res
        );
    }

    @Test
    void it_should_validate_negative_max_divided_by_1() {
        // GIVEN
        var dividend = Integer.MIN_VALUE;
        var divisor = 1;

        // WHEN
        var res = new DivideTwoIntegers().divide(dividend, divisor);

        // THEN
        assertEquals(
                Integer.MIN_VALUE,
                res
        );
    }

    @Test
    void it_should_validate_negative_max_divided_by_2() {
        // GIVEN
        var dividend = Integer.MIN_VALUE;
        var divisor = 2;

        // WHEN
        var res = new DivideTwoIntegers().divide(dividend, divisor);

        // THEN
        assertEquals(
                -1073741824,
                res
        );
    }

    @Test
    void it_should_validate_positive_max() {
        // GIVEN
        var dividend = 2147483647;
        var divisor = 1;

        // WHEN
        var res = new DivideTwoIntegers().divide(dividend, divisor);

        // THEN
        assertEquals(
                2147483647,
                res
        );
    }

    @Test
    void it_should_validate_negative_max_division() {
        // GIVEN
        var dividend = Integer.MAX_VALUE;
        var divisor = Integer.MIN_VALUE;

        // WHEN
        var res = new DivideTwoIntegers().divide(dividend, divisor);

        // THEN
        assertEquals(
                0,
                res
        );
    }

    @Test
    void it_should_validate_positive_max_division_by_itself() {
        // GIVEN
        var dividend = Integer.MAX_VALUE;
        var divisor = Integer.MAX_VALUE;

        // WHEN
        var res = new DivideTwoIntegers().divide(dividend, divisor);

        // THEN
        assertEquals(
                1,
                res
        );
    }

    @Test
    void it_should_validate_negative_max_division_by_itself() {
        // GIVEN
        var dividend = Integer.MIN_VALUE;
        var divisor = Integer.MIN_VALUE;

        // WHEN
        var res = new DivideTwoIntegers().divide(dividend, divisor);

        // THEN
        assertEquals(
                1,
                res
        );
    }
}