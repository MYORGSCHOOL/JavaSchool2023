package malakhova;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ThreadStatesTest {
    @Test
    @DisplayName("Получение состояния NEW")
    public void testSuccessGetStateNew() {
        Thread thread = new Thread();
        Assertions.assertEquals(thread.getState().toString(), "NEW");
    }

    @Test
    @DisplayName("Получение состояния RUNNABLE")
    public void testSuccessGetStateRunnable() {
        Runnable runnable = () -> {
        };
        Thread thread = new Thread(runnable);
        thread.start();
        Assertions.assertEquals(thread.getState().toString(), "RUNNABLE");
    }

    @Test
    @DisplayName("Получение состояния TIMED-WAITING")
    public void testSuccessGetStateTimedWaiting() throws InterruptedException {
        Runnable runnable = () -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
        thread.sleep(1000);
        Assertions.assertEquals(thread.getState().toString(), "TIMED_WAITING");
    }

    @Test
    @DisplayName("Получение состояния BLOCKED")
    public void testSuccessGetStateBlocked() throws InterruptedException {
        Runnable runnable = () -> {
            try {
                SynchronizedThread.blockedState();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };
        Runnable runnable1 = () -> {
            try {
                SynchronizedThread.blockedState();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
        Thread thread1 = new Thread(runnable1);
        thread1.start();
        Thread.sleep(1000);
        Assertions.assertEquals(thread1.getState().toString(), "BLOCKED");
    }

    @Test
    @DisplayName("Получение состояния WAITING")
    public void testSuccessGetStateWaiting() throws InterruptedException {
        Thread thread = new Thread(new SynchronizedThread().new WaitingThread());
        Thread thread1 = new Thread(new SynchronizedThread().new WaitingThread());
        thread1.start();
        thread.start();
        thread.sleep(1000);
        Assertions.assertEquals(thread.getState().toString(), "WAITING");
        thread.interrupt();
        thread1.interrupt();
    }

    @Test
    @DisplayName("Получение состояния TERMINATED")
    public void testSuccessGetStateTerminated() throws InterruptedException {
        Runnable runnable = () -> {
        };
        Thread thread = new Thread(runnable);
        thread.start();
        thread.sleep(1000);
        Assertions.assertEquals(thread.getState().toString(), "TERMINATED");
    }
}
