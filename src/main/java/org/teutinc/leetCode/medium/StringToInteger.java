/*
    Implement atoi which converts a string to an integer.

    The function first discards as many whitespace characters as necessary until the first non-whitespace character is found. Then, starting from this character, takes an optional initial plus or minus sign followed by as many numerical digits as possible, and interprets them as a numerical value.

    The string can contain additional characters after those that form the integral number, which are ignored and have no effect on the behavior of this function.

    If the first sequence of non-whitespace characters in str is not a valid integral number, or if no such sequence exists because either str is empty or it contains only whitespace characters, no conversion is performed.

    If no valid conversion could be performed, a zero value is returned.

    Note:
        - Only the space character ' ' is considered as whitespace character.
        - Assume we are dealing with an environment which could only store integers within the 32-bit signed integer range: [−231,  231 − 1]. If the numerical value is out of the range of representable values, INT_MAX (231 − 1) or INT_MIN (−231) is returned.

 */
package org.teutinc.leetCode.medium;


import org.teutinc.leetCode.util.TestRunner;

public class StringToInteger {
    private static int myAtoi(String str) {
        if (str.isEmpty()) {
            return 0;
        }

        // find min bounds and sign
        int index = 0;
        int sign = 1; // default is positive

        char[] chars = str.toCharArray();

        // remove initial spaces
        while (index < chars.length && chars[index] == ' ') index++;

        if (index == chars.length) {
            // nothing in the string... just spaces
            return 0;
        }

        // analyze sign if defined
        if (chars[index] == '-') {
            sign = -1;
            index++;
        } else if (chars[index] == '+') {
            index++;
        }

        if (index == chars.length) {
            // nothing in the string... just 0 to n spaces and a sign
            return 0;
        }

        // now let's analyze some digits...
        int result = 0;
        int limit = sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
        int tenthOfLimit = limit / 10;
        for (;
             index < chars.length &&
                     chars[index] >= '0' && // ascii code for '0'
                     chars[index] <= '9'; // ascii code for '9'
             index++
        ) {
            if (Integer.compare(result, tenthOfLimit) == sign) { // multiply in order to get only positives...
                // already to big...
                result = limit;
                break;
            }

            int digit = chars[index] - '0';
            result *= 10;
            // check if there is enough space left
            if (Integer.compare(sign * digit, limit - result) == sign) {
                // the digit will provoke overflow...
                result = limit;
                break;
            }

            result += sign * digit;
        }

        return result;
    }

    static class Test implements TestRunner.RunnableTest<Integer> {
        private final String input;
        private final int expected;

        Test(String input, int expected) {
            this.input = input;
            this.expected = expected;
        }

        @Override
        public Integer run() {
            return myAtoi(input);
        }

        @Override
        public String inputs() {
            return input;
        }

        @Override
        public Integer expect() {
            return expected;
        }
    }

    public static void main(String[] args) {
        TestRunner.basic()
                  .run(
                          new Test("12", 12),
                          new Test("12      ", 12),
                          new Test("     123", 123),
                          new Test("     +123", 123),
                          new Test("  -1", -1),
                          new Test("  -1 foo", -1),
                          new Test("8a", 8),
                          new Test(" a78 ", 0),
                          new Test(Integer.toString(Integer.MAX_VALUE), Integer.MAX_VALUE),
                          new Test(Integer.toString(Integer.MIN_VALUE), Integer.MIN_VALUE),
                          new Test("2147483649", Integer.MAX_VALUE),
                          new Test("-2147483649", Integer.MIN_VALUE)
                  );
    }
}
