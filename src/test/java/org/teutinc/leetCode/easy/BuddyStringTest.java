package org.teutinc.leetCode.easy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BuddyStringTest {
    @Test
    void it_should_validate_example_1() {
        // GIVEN
        var a = "ab";
        var b = "ba";

        // WHEN
        var res = new BuddyString().buddyStrings(a, b);

        // THEN
        assertTrue(res);
    }

    @Test
    void it_should_validate_example_2() {
        // GIVEN
        var a = "ab";
        var b = "ab";

        // WHEN
        var res = new BuddyString().buddyStrings(a, b);

        // THEN
        assertFalse(res);
    }

    @Test
    void it_should_validate_example_3() {
        // GIVEN
        var a = "aa";
        var b = "aa";

        // WHEN
        var res = new BuddyString().buddyStrings(a, b);

        // THEN
        assertTrue(res);
    }

    @Test
    void it_should_validate_example_4() {
        // GIVEN
        var a = "aaaaaaabc";
        var b = "aaaaaaacb";

        // WHEN
        var res = new BuddyString().buddyStrings(a, b);

        // THEN
        assertTrue(res);
    }

    @Test
    void it_should_validate_example_5() {
        // GIVEN
        var a = "";
        var b = "aa";

        // WHEN
        var res = new BuddyString().buddyStrings(a, b);

        // THEN
        assertFalse(res);
    }

    @Test
    void it_should_validate_example_6() {
        // GIVEN
        var a = "abac";
        var b = "abad";

        // WHEN
        var res = new BuddyString().buddyStrings(a, b);

        // THEN
        assertFalse(res);
    }
}