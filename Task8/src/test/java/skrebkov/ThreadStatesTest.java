package skrebkov;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ThreadStatesTest {
    /**
     * Тест для получения состояния потока NEW
     */
    @Test
    @DisplayName("Получаем состояние NEW")
    public void testThreadNewState() {
        Thread thread = new Thread();
        Assertions.assertEquals(Thread.State.NEW, thread.getState());
    }

    /**
     * Тест для получения состояния потока RUNNABLE
     */
    @Test
    @DisplayName("Получаем состояние RUNNABLE")
    public void testThreadRunnableState() {
        Thread thread = new Thread();
        thread.start();
        Assertions.assertEquals(Thread.State.RUNNABLE, thread.getState());
    }

    /**
     * Тест для получения состояния потока WAITING
     */
    @Test
    @DisplayName("Получаем состояние WAITING")
    public void testThreadWantingState() throws InterruptedException {
        Object mutex = new Object();
        Thread thread = new Thread(() -> {
            synchronized (mutex) {
                try {
                    mutex.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        thread.start();
        Thread.sleep(1000);
        Assertions.assertEquals(Thread.State.WAITING, thread.getState());
        synchronized (mutex) {
            mutex.notify();
        }
    }

    /**
     * Тест для получения состояния потока TIMED_WAITING
     */
    @Test
    @DisplayName("Получаем состояние TIMED_WAITING")
    public void testThreadTimedWaitingState() throws InterruptedException {
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        thread.start();
        Thread.sleep(10);
        Assertions.assertEquals(Thread.State.TIMED_WAITING, thread.getState());
    }

    /**
     * Тест для получения состояния потока TERMINATED
     */
    @Test
    @DisplayName("Получаем состояние TERMINATED")
    public void testThreadTerminatedState() throws InterruptedException {
        Thread thread = new Thread();
        thread.start();
        Thread.sleep(100);
        Assertions.assertEquals(Thread.State.TERMINATED, thread.getState());
    }

    /**
     * Тест для получения состояния потока BLOCKED
     */
    @Test
    @DisplayName("Получаем состояние BLOCKED")
    public void testThreadBlockedState() throws InterruptedException {
        Object mutex = new Object();
        Thread thread1 = new Thread(() -> {
            synchronized (mutex) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            synchronized (mutex) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        thread1.start();

        Thread.sleep(10);
        thread2.start();

        Thread.sleep(10);
        Assertions.assertEquals(Thread.State.BLOCKED, thread2.getState());
    }
}
