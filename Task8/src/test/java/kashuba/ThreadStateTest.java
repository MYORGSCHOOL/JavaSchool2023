package kashuba;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ThreadStateTest {
    private Thread thread;
    private final Object mutex = new Object();

    @Test
    @DisplayName("Тест состояния NEW")
    public void newStatusTest() {
        thread = new Thread();
        Assertions.assertEquals(Thread.State.NEW, thread.getState());
    }

    @Test
    @DisplayName("Тест состояния RUNNABLE")
    public void runnableStatusTest() {
        thread = new Thread();
        thread.start();
        Assertions.assertEquals(Thread.State.RUNNABLE, thread.getState());
    }

    @Test
    @DisplayName("Тест состояния BLOCKED")
    public void blockedStatusTest() throws InterruptedException {
        Thread thread = new Thread(() -> {
            try {
                synchronized (mutex) {
                    mutex.wait();
                }
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        });
        thread.start();
        synchronized (mutex) {
            Thread.sleep(500);
            Assertions.assertEquals(Thread.State.BLOCKED, thread.getState());
            mutex.notify();
        }
    }

    @Test
    @DisplayName("Тест состояния WAITING")
    public void waitingStatusTest() {
        thread = new Thread(() -> {
            synchronized (mutex) {
                try {
                    mutex.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            Assertions.assertEquals(Thread.State.WAITING, thread.getState().toString());
        });
        thread.start();
    }

    @Test
    @DisplayName("Тест состояния TIMED_WAITING")
    public void limitedWaitingStateTest() {
        thread = new Thread(() -> {
            synchronized (mutex) {
                try {
                    mutex.wait(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            Assertions.assertEquals(Thread.State.TIMED_WAITING, thread.getState().toString());
        });
        thread.start();
    }

    @Test
    @DisplayName("Тест состояния TERMINATED")
    public void terminatedStateTest() throws InterruptedException {
        thread = new Thread();
        thread.start();
        thread.join();
        Assertions.assertEquals(Thread.State.TERMINATED, thread.getState());
    }
}
