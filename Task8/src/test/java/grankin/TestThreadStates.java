package grankin;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestThreadStates {

    @Test
    @DisplayName("State NEW")
    public void testNew() {
        ThreadStates mytest = new ThreadStates();
        Assertions.assertEquals("NEW", mytest.testNewThreadState());
    }

    @Test
    @DisplayName("State RUNNABLE")
    public void testRunnable() {
        ThreadStates mytest = new ThreadStates();
        Assertions.assertEquals("RUNNABLE", mytest.testRunnableThreadState());
    }

    @Test
    @DisplayName("State TERMINATED")
    public void testTerminated() {
        ThreadStates mytest = new ThreadStates();
        try {
            Assertions.assertEquals("TERMINATED", mytest.testTerminatedThreadState());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("State BLOCKED")
    public void testBlocked() {
        ThreadStates mytest = new ThreadStates();
        try {
            Assertions.assertEquals("BLOCKED", mytest.testBlockedThreadState());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("State WAITING")
    public void testWaiting() {
        ThreadStates mytest = new ThreadStates();
        try {
            Assertions.assertEquals("WAITING", mytest.testWaitingThreadState());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("State TIMED_WAITING")
    public void testTimedWaiting() {
        ThreadStates mytest = new ThreadStates();
        try {
            Assertions.assertEquals("TIMED_WAITING", mytest.testTimedWaitingThreadState());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
