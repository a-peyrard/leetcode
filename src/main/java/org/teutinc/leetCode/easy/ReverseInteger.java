package org.teutinc.leetCode.easy;

import org.teutinc.leetCode.util.TestRunner;

public class ReverseInteger {

    private static int reverse(int x) {
        if (x == Integer.MIN_VALUE) {
            return 0; // shortcut as Math.abs will fail on this :/
        }

        int unsignedX = Math.abs(x);
        if (unsignedX < 10) {
            // shortcut
            return x;
        }

        int sign = x < 0 ? -1 : 1;

        int log = (int) Math.ceil(Math.log10(unsignedX)); // get the number of digits

        // another shortcut for 10, 100, 1000 as they will not count the digits correctly for them
        if (unsignedX == Math.pow(10, log)) {
            return sign;
        }

        int result = (int)((unsignedX % 10) * Math.pow(10, log - 1));
        if (result == Integer.MAX_VALUE) {
            return 0;
        }
        for (int i = 1; i < log; i++) {
            int number = (int) (unsignedX / Math.pow(10, i) % 10);
            result += number * Math.pow(10, log - 1 - i);
        }

        if (result == Integer.MAX_VALUE) {
            return 0; // corner case, I don't see why they are not accepting this, this not an overflow!
        }

        return sign * result;
    }

    static class Test implements TestRunner.RunnableTest<Integer> {
        private final int input;
        private final int expected;


        Test(int input, int expected) {
            this.input = input;
            this.expected = expected;
        }

        @Override
        public Integer run() {
            return reverse(input);
        }

        @Override
        public String inputs() {
            return Integer.toString(input);
        }

        @Override
        public Integer expect() {
            return expected;
        }
    }


    public static void main(String[] args) {
        TestRunner.basic()
                  .run(
                          new Test(1563847412, 0),
                          new Test(-2147483648, 0),
                          new Test(Integer.MAX_VALUE, 0),
                          new Test(1534236469, 0),
                          new Test(100, 1),
                          new Test(123, 321),
                          new Test(-120, -21),
                          new Test(12, 21),
                          new Test(-7, -7)
                  );
    }
}
