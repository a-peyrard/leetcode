package hard;

/*
    Validate if a given string can be interpreted as a decimal number.

    Some examples:
    "0" => true
    " 0.1 " => true
    "abc" => false
    "1 a" => false
    "2e10" => true
    " -90e3   " => true
    " 1e" => false
    "e3" => false
    " 6e-1" => true
    " 99e2.5 " => false
    "53.5e93" => true
    " --6 " => false
    "-+3" => false
    "95a54e53" => false

    Note: It is intended for the problem statement to be ambiguous. You should gather all requirements up front before implementing one. However, here is a list of characters that can be in a valid decimal number:

        Numbers 0-9
        Exponent - "e"
        Positive/negative sign - "+"/"-"
        Decimal point - "."

    Of course, the context of these characters also matters in the input.
 */

import util.TestRunner;

import java.util.List;
import java.util.regex.Pattern;

public class ValidNumber {
    private static final Pattern regex = Pattern.compile(
            "^\\s*[-+]?((\\d*\\.)?\\d+|\\d+(\\.\\d*)?)(e[-+]?\\d+)?\\s*$"
    );

    private static boolean isNumber(String s) {
        return regex.matcher(s).find();
    }

    static class Test implements TestRunner.RunnableTest<Boolean> {
        private final String input;
        private final boolean output;

        Test(String input, boolean output) {
            this.input = input;
            this.output = output;
        }

        @Override
        public Boolean run() {
            return isNumber(input);
        }

        @Override
        public String inputs() {
            return input;
        }

        @Override
        public Boolean expect() {
            return output;
        }
    }

    public static void main(String[] args) {
        TestRunner.basic()
                  .run(
                          new Test("46.e3", true),
                          new Test(".e3", false),
                          new Test("-.", false),
                          new Test("+.", false),
                          new Test("+e", false),
                          new Test("-e", false),
                          new Test("-.e", false),
                          new Test(".e", false),
                          new Test(" ", false),
                          new Test(" .", false),
                          new Test(".", false),
                          new Test("-", false),
                          new Test("+", false),
                          new Test("897.", true),
                          new Test("+897.", true),
                          new Test(".9e3", true),
                          new Test(".9", true),
                          new Test("-.9", true),
                          new Test("0", true),
                          new Test(" 0.1 ", true),
                          new Test("abc", false),
                          new Test("1 a", false),
                          new Test("2e10", true),
                          new Test(" -90e3   ", true),
                          new Test(" 1e", false),
                          new Test("e3", false),
                          new Test(" 6e-1", true),
                          new Test(" 99e2.5 ", false),
                          new Test("53.5e93", true),
                          new Test(" --6 ", false),
                          new Test("-+3", false),
                          new Test("95a54e53", false),
                          new Test(" 005047e+6", true)
                  );
    }
}


