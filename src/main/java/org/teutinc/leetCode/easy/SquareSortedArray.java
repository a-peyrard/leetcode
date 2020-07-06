package org.teutinc.leetCode.easy;

import org.teutinc.leetCode.util.TestRunner;

import java.util.Arrays;

/*
    Given an array of integers A sorted in non-decreasing order,
    return an array of the squares of each number, also in sorted non-decreasing order.
 */

public class SquareSortedArray {

    private static int[] sortedSquares(int[] A) {
        if (A.length == 0) {
            return A;
        }
        if (A.length == 1) {
            A[0] *= A[0];
            return A;
        }
        if (A[0] >= 0) { // only positives
            for (int i = 0; i < A.length; i++) {
                A[i] *= A[i];
            }
            return A;
        }
        if (A[A.length - 1] <= 0) { // only negatives
            for (int i = 0; i < A.length / 2; i++) {
                int head = A[i];
                int tail = A[A.length - 1 -i];
                A[i] = tail * tail;
                A[A.length - 1 - i] = head * head;
            }
            if (A.length % 2 == 1) { // for odd length, do the square of the middle cell
                A[A.length / 2] *= A[A.length / 2];
            }

            return A;
        }

        // mixed content, lets find the pivot (last negative number)
        final int pivot = findPivot(A, 0, A.length - 1);
        int negativeIdx = pivot;
        int positiveIdx = pivot + 1;
        int[] res = new int[A.length];
        int resIdx = 0;
        while (negativeIdx >= 0 || positiveIdx <= A.length - 1) {
            if (positiveIdx >= A.length || (negativeIdx >= 0 && -1 * A[negativeIdx] < A[positiveIdx])) {
                res[resIdx] = A[negativeIdx] * A[negativeIdx];
                negativeIdx--;
            } else {
                res[resIdx] = A[positiveIdx] * A[positiveIdx];
                positiveIdx++;
            }
            resIdx++;
        }

        return res;
    }

    private static int findPivot(int[] a, int min, int max) {
        int pivot = ((max - min) / 2) + min;
        if (a[pivot] < 0 && a[pivot + 1] >= 0) {
            return pivot;
        }
        if (a[pivot] < 0) {
            return findPivot(a, pivot, max);
        }

        return findPivot(a, min, pivot);
    }


    static class Test implements TestRunner.RunnableTest<int[]> {
        private final int[] inputCopy;
        private final int[] input;
        private final int[] expected;


        Test(int[] input, int[] expected) {
            this.input = input;
            this.inputCopy = Arrays.copyOf(input, input.length);
            this.expected = expected;
        }

        @Override
        public int[] run() {
            return sortedSquares(inputCopy);
        }

        @Override
        public String inputs() {
            return Arrays.toString(input);
        }

        @Override
        public int[] expect() {
            return expected;
        }
    }


    public static void main(String[] args) {
        TestRunner.basic()
                  .run(
                          new Test(new int[]{}, new int[]{}),
                          new Test(new int[]{-4}, new int[]{16}),
                          new Test(new int[]{5}, new int[]{25}),
                          new Test(new int[]{-4, 5}, new int[]{16, 25}),
                          new Test(new int[]{-8, 5}, new int[]{25, 64}),
                          new Test(new int[]{-4, -1, 0, 3, 10}, new int[]{0, 1, 9, 16, 100}),
                          new Test(new int[]{-7, -3, 2, 3, 11}, new int[]{4, 9, 9, 49, 121}),
                          new Test(new int[]{0, 1, 2, 3, 4}, new int[]{0, 1, 4, 9, 16}),
                          new Test(new int[]{-10, -9, -8, 1, 2}, new int[]{1, 4, 64, 81, 100}),
                          new Test(new int[]{-10, -9, -8, -1, 0}, new int[]{0, 1, 64, 81, 100}),
                          new Test(new int[]{-10, -9, -8, -1, 5, 6}, new int[]{1, 25, 36, 64, 81, 100}),
                          new Test(new int[]{-10, -9, -8, -1, 5}, new int[]{1, 25, 64, 81, 100}),
                          new Test(new int[]{-10, -9, -8, -1}, new int[]{1, 64, 81, 100}),
                          new Test(new int[]{-3, -2, -1, 5, 6}, new int[]{1, 4, 9, 25, 36})
                  );
    }
}
