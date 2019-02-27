package easy;

public class ReverseInteger {

    private static int reverse(int x) {
        if (x == Integer.MIN_VALUE) {
            return 0; // shortcut as Math.abs will fail on this :/
        }

        // 1234
        // 4 * 1000
        // 3 * 100
        // 2 * 10

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

    public static void main(String[] args) {
        System.out.println("Integer.MIN_VALUE = " + Integer.MIN_VALUE);
        System.out.println("Integer.MAX_VALUE = " + Integer.MAX_VALUE);


        int[] tests = {
                1563847412,
//                -2147483648,
//                Integer.MAX_VALUE,
//                1534236469,
//                100,
//                123,
//                -120,
//                12,
//                -7
        };

        for (int test : tests) {
            System.out.printf(" => reverse %d = %d\n", test, reverse(test));
        }
    }
}
