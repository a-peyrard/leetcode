package util;

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
            boolean pass = test.expect().equals(res);
            if (pass) {
                nbSuccess++;
            }
            System.out.printf(
                    "%s for (%s) = %s expected %s\n",
                    pass ? "✨" : "\uD83D\uDC80",
                    test.inputs(),
                    res,
                    test.expect()
            );
        }

        System.out.printf(
                "\n\n => (%d/%d) %s\n",
                nbSuccess,
                tests.length,
                nbSuccess == tests.length ? "\uD83D\uDC4D" : "\uD83D\uDC4E"
        );
    }
}
