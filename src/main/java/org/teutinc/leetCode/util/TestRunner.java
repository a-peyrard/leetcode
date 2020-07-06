package org.teutinc.leetCode.util;

import java.util.Arrays;

public class TestRunner {
    public static TestRunner basic() {
        return new TestRunner();
    }

    public interface RunnableTest<O> {
        O run();

        String inputs();
        O expect();
    }

    public <O> void run(RunnableTest<O>... tests) {
        int nbSuccess = 0;
        for (RunnableTest<O> test : tests) {
            O res = test.run();
            boolean pass = assertEquals(test.expect(), res);
            if (pass) {
                nbSuccess++;
            }
            System.out.printf(
                    "%s for (%s) = %s expected %s\n",
                    pass ? "✨" : "\uD83D\uDC80",
                    test.inputs(),
                    print(res),
                    print(test.expect())
            );
        }

        System.out.printf(
                "\n\n => (%d/%d) %s\n",
                nbSuccess,
                tests.length,
                nbSuccess == tests.length ? "\uD83D\uDC4D" : "\uD83D\uDC4E"
        );
    }

    private boolean assertEquals(Object a, Object b) {
        if (a instanceof int[] && b instanceof int[]) {
            return Arrays.equals((int[]) a, (int[]) b);
        }
        return a.equals(b);
    }

    private String print(Object o) {
        if (o instanceof int[]) {
            return Arrays.toString((int[]) o);
        }

        return String.valueOf(o);
    }
}
