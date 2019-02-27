/*
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

public class JewelsAndStones {

    public static final int BASE_INDEX = (int) 'A'; // 65

    public static int numJewelsInStones(String J, String S) {
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

    public static int index(char c) {
        return c - BASE_INDEX;
    }

    public static class Test {
        String jewels;
        String stones;
        int expected;

        public Test(String jewels, String stones, int expected) {
            this.jewels = jewels;
            this.stones = stones;
            this.expected = expected;
        }
    }

    public static void main(String[] args) {
        Test[] tests = {
                new Test("aB", "abcdefabcdef", 2),
                new Test("abc", "abcdefabcdef", 6),
                new Test("abc", "zyWdEF", 0),
        };

        for (Test test : tests) {
            int nb = numJewelsInStones(test.jewels, test.stones);
            System.out.printf(
                    "%s nb for (%s/%s) = %d expected %d\n",
                    test.expected == nb ? "\uD83C\uDF89" : "\uD83D\uDC80",
                    test.jewels,
                    test.stones,
                    nb,
                    test.expected
            );
        }
    }
}
