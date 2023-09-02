package tumbaev;

import tumbaev.exception.AssertionFailError;

/**
 * TestAssertions is a collection of utility methods that support asserting conditions in tests
 */
public final class TestAssertions {
    private TestAssertions() {
        throw new UnsupportedOperationException("This is an utility class");
    }

    /**
     * Checks if condition is true. If not, exception is thrown.
     *
     * @param condition true if test succeed, false otherwise
     * @param message   message that will be displayed if test fails
     * @throws AssertionFailError test failed
     */
    public static void doAssert(boolean condition, String message) {
        if (!condition) {
            throw new AssertionFailError(message);
        }
    }
}
