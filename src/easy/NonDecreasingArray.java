package easy;/*
    Given an array with n integers, your task is to check if it could become non-decreasing by modifying at most 1 element.

    We define an array is non-decreasing if array[i] <= array[i + 1] holds for every i (1 <= i < n).

    Example 1:

    Input: [4,2,3]
    Output: True
    Explanation: You could modify the first 4 to 1 to get a non-decreasing array.

    Example 2:

    Input: [4,2,1]
    Output: False
    Explanation: You can't get a non-decreasing array by modify at most one element.

 */

import util.TestRunner;

import java.util.Arrays;

public class NonDecreasingArray {
    private static boolean checkPossibility(int[] nums) {
        int errors = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] > nums[i + 1]) {
                errors++;
                // no we need to analyze what would be the ideal value for this, and if it fits
                if (i > 0) {
                    int ideal = nums[i - 1];
                    if (ideal > nums[i + 1]) {
                        // so this fix is not working, maybe we can fix a[i+1]
                        ideal = nums[i];
                        if (i < nums.length - 2) {
                            if (ideal > nums[i + 2]) {
                                return false;
                            }
                        }
                    }
                }
            }
            if (errors > 1) {
                return false;
            }
        }
        return true;
    }

    static class Test implements TestRunner.RunnableTest<Boolean> {
        private final int[] input;
        private final boolean output;

        Test(int[] input, boolean output) {
            this.input = input;
            this.output = output;
        }

        @Override
        public Boolean run() {
            return checkPossibility(input);
        }

        @Override
        public String inputs() {
            return Arrays.toString(input);
        }

        @Override
        public Boolean expect() {
            return output;
        }
    }

    public static void main(String[] args) {
        TestRunner.basic()
                  .run(
                          new Test(new int[]{3, 4, 2, 3}, false),
                          new Test(new int[]{11, 2, 3, 4}, true),
                          new Test(new int[]{1, 11, 3, 4}, true),
                          new Test(new int[]{1, 2, 3, 4}, true),
                          new Test(new int[]{4, 3, 1, 4}, false),
                          new Test(new int[]{1, 2, 3, 1}, true),
                          new Test(new int[]{1, 2, 3, 1, 5}, true),
                          new Test(new int[]{5, 2, 3, 1}, false)
                  );
    }
}
