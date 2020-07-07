package org.teutinc.leetCode.medium;
/*
Given two integers dividend and divisor, divide two integers without using multiplication, division and mod operator.

Return the quotient after dividing dividend by divisor.

The integer division should truncate toward zero, which means losing its fractional part. For example, truncate(8.345) = 8 and truncate(-2.7335) = -2.

Example 1:

Input: dividend = 10, divisor = 3
Output: 3
Explanation: 10/3 = truncate(3.33333..) = 3.

Example 2:

Input: dividend = 7, divisor = -3
Output: -2
Explanation: 7/-3 = truncate(-2.33333..) = -2.

Note:

    Both dividend and divisor will be 32-bit signed integers.
    The divisor will never be 0.
    Assume we are dealing with an environment which could only store integers within the 32-bit signed integer range: [−231,  231 − 1]. For the purpose of this problem, assume that your function returns 231 − 1 when the division result overflows.


 */

public class DivideTwoIntegers {
    static int getSign(int v) {
        return v >> 31;
    }

    static int getAbs(int v) {
        if (v == Integer.MIN_VALUE) {
            return Integer.MAX_VALUE;
        }
        int mask = v >> 31;
        return (v ^ mask) - mask;
    }

    static int sign(int v, int sign) {
        return sign == -1 ? -v : v;
    }

    public int divide(int dividend, int divisor) {
        if (divisor == 1) {
            return dividend;
        }
        if (divisor == 2) {
            return dividend >> 1;
        }
        if (divisor == Integer.MIN_VALUE) {
            return dividend == Integer.MIN_VALUE ? 1 : 0;
        }

        int absDividend = getAbs(dividend);
        final int absDivisor = getAbs(divisor);

        if (absDivisor > absDividend) {
            return 0;
        }

        final int signDividend = getSign(dividend);
        final int signDivisor = getSign(divisor);

        // some shortcuts
        if (absDivisor == 1) {
            return sign(absDividend, signDividend + signDivisor);
        }
        if (absDivisor == 2) {
            return sign(absDividend >> 1, signDividend + signDivisor);
        }

        // compute result
        int res = 0;
        while (absDividend >= absDivisor) {
            absDividend -= absDivisor;
            res++;
        }

        return sign(res, signDividend + signDivisor);
    }
}
