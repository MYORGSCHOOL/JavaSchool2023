package tumbaev;

import java.util.Optional;

/**
 * Reflects result of a test
 */
public class TestResult {

    /**
     * Indicates if test succeed or not
     */
    private final boolean successful;

    /**
     * Name of the test method
     */
    private final String testMethodName;

    /**
     * Message of the test (optional)
     */
    private final String message;

    /**
     * Cause of the test (optional)
     */
    private final Throwable cause;

    public TestResult(boolean successful, String testMethodName, String message, Throwable cause) {
        this.successful = successful;
        this.testMethodName = testMethodName;
        this.message = message;
        this.cause = cause;
    }

    public boolean isSuccessful() {
        return successful;
    }

    /**
     * Name of test method, execution of which produced this result
     *
     * @return name of test method
     */
    public String getTestMethodName() {
        return testMethodName;
    }

    /**
     * Message of the test
     *
     * @return empty if there is no message, message otherwise
     */
    public Optional<String> getMessage() {
        return Optional.ofNullable(message);
    }

    /**
     * Cause of the test
     *
     * @return empty if there is no cause, cause otherwise
     */
    public Optional<Throwable> getCause() {
        return Optional.ofNullable(cause);
    }

    public static TestResultBuilder builder(boolean successful, String testMethodName) {
        return new TestResultBuilder(successful, testMethodName);
    }

    public static class TestResultBuilder {
        private final boolean successful;
        private final String testMethodName;
        private String message;
        private Throwable cause;

        TestResultBuilder(boolean successful, String testMethodName) {
            this.successful = successful;
            this.testMethodName = testMethodName;
        }

        public TestResultBuilder message(String message) {
            this.message = message;
            return this;
        }

        public TestResultBuilder cause(Throwable cause) {
            this.cause = cause;
            return this;
        }

        public TestResult build() {
            return new TestResult(this.successful, this.testMethodName, this.message, this.cause);
        }
    }
}
