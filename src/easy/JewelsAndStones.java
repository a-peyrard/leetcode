package easy;/*
    You're given strings J representing the types of stones that are jewels, and S representing the stones you have.  Each character in S is a type of stone you have.  You want to know how many of the stones you have are also jewels.

    The letters in J are guaranteed distinct, and all characters in J and S are letters. Letters are case sensitive, so "a" is considered a different type of stone from "A".

    Example 1:

    Input: J = "aA", S = "aAAbbbb"
    Output: 3

    Example 2:

    Input: J = "z", S = "ZZ"
    Output: 0

    Note:

    S and J will consist of letters and have length at most 50.
    The characters in J are distinct.
 */

import util.TestRunner;

public class JewelsAndStones {

    private static final int BASE_INDEX = (int) 'A'; // 65

    private static int numJewelsInStones(String J, String S) {
        long jewels = 0;
        for (char c : J.toCharArray()) {
            int index = index(c);
            jewels |= (long) 1 << index;
        }

        int count = 0;
        for (char c : S.toCharArray()) {
            int index = index(c);
            count += (jewels & ((long) 1 << index)) >> index;
        }

        return count;
    }

    private static int index(char c) {
        return c - BASE_INDEX;
    }

    public static class Test implements TestRunner.RunnableTest<Integer> {
        private final String jewels;
        private final String stones;
        private final int expected;


        Test(String jewels, String stones, int expected) {
            this.jewels = jewels;
            this.stones = stones;
            this.expected = expected;
        }

        @Override
        public Integer run() {
            return numJewelsInStones(jewels, stones);
        }

        @Override
        public String inputs() {
            return String.format("%s/%s", jewels, stones);
        }

        @Override
        public Integer expect() {
            return expected;
        }
    }

    public static void main(String[] args) {
        TestRunner.basic()
                  .run(
                          new Test("aB", "abcdefabcdef", 2),
                          new Test("abc", "abcdefabcdef", 6),
                          new Test("abc", "zyWdEF", 0)
                  );
    }
}
