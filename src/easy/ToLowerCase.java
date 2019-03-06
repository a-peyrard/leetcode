package easy;

import util.TestRunner;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/*
    Implement function ToLowerCase() that has a string parameter str,
    and returns the same string in lowercase.
 */
public class ToLowerCase {
    private static final int UPPER_TO_LOWER = 'a' - 'A';
    private static final int MIN_BOUND = 'A';
    private static final int MAX_BOUND = 'Z';

    private static String toLowerCase(String str) {
        if (str.isEmpty()) {
            return str;
        }
        boolean modified = false;
        char[] characters = str.toCharArray();
        for (int i = 0; i < characters.length; i++) {
            if (characters[i] >= MIN_BOUND && characters[i] <= MAX_BOUND) {
                characters[i] += UPPER_TO_LOWER;
                modified = true;
            }
        }
        return modified ? new String(characters) : str;
    }

    static class Test implements TestRunner.RunnableTest<String> {
        private final String input;
        private final String output;

        Test(String input, String output) {
            this.input = input;
            this.output = output;
        }

        @Override
        public String run() {
            return toLowerCase(input);
        }

        @Override
        public String inputs() {
            return input;
        }

        @Override
        public String expect() {
            return output;
        }
    }

    public static void main(String[] args) {
        TestRunner.basic()
                  .run(
                          new Test("Hello", "hello"),
                          new Test("here", "here"),
                          new Test("Lovely", "lovely"),
                          new Test("Hello World: AUGUSTIN! ?", "hello world: augustin! ?")
                  );
    }
}
