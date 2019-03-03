package util;

import java.util.List;

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
        boolean success = true;
        for (RunnableTest<O> test : tests) {
            O res = test.run();
            boolean pass = test.expect().equals(res);
            if (!pass) {
                success = false;
            }
            System.out.printf(
                    "%s for (%s) = %s expected %s\n",
                    pass ? "✨" : "\uD83D\uDC80",
                    test.inputs(),
                    res,
                    test.expect()
            );
        }

        if (success) {
            System.out.println("\n\uD83D\uDC4D\n");
        } else {
            System.out.println("\n\uD83D\uDC4E\n");
        }
    }
}
