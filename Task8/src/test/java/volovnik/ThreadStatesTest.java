package volovnik;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ThreadStatesTest {

    @Test
    @DisplayName("Тест состояния потока: NEW")
    public void testSuccessThreadStateNew() {
        Thread thread = new Thread();
        Assertions.assertEquals(Thread.State.NEW, thread.getState());
    }

    @Test
    @DisplayName("Тест состояния потока: RUNNABLE")
    public void testSuccessThreadStateRunnable() {
        Thread thread = new Thread();
        thread.start();
        Assertions.assertEquals(Thread.State.RUNNABLE, thread.getState());
    }

    @Test
    @DisplayName("Тест состояния потока: TERMINATED")
    public void testSuccessThreadStateTerminated() throws InterruptedException {
        Thread thread = new Thread();
        thread.start();
        thread.join();
        Assertions.assertEquals(Thread.State.TERMINATED, thread.getState());
    }

    @Test
    @DisplayName("Тест состояния потока: WAITING")
    public void testSuccessThreadStateWaiting() throws InterruptedException {
        Object obj = new Object();
        Thread thread = new Thread(() -> {
            try {
                synchronized (obj) {
                    obj.wait();
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        thread.start();
        Thread.sleep(500L);
        synchronized (obj) {
            Assertions.assertEquals(Thread.State.WAITING, thread.getState());
            obj.notify();
        }
    }

    @Test
    @DisplayName("Тест состояния потока: TIMED_WAITING")
    public void testSuccessThreadStateTimedWaiting() throws InterruptedException {
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(500L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        thread.start();
        Thread.sleep(250L);
        Assertions.assertEquals(Thread.State.TIMED_WAITING, thread.getState());
    }

    @Test
    @DisplayName("Тест состояния потока: BLOCKED")
    public void testSuccessThreadStateBlocked() throws InterruptedException {
        Object obj = new Object();
        Thread thread1 = new Thread(() -> {
            synchronized (obj) {
                try {
                    Thread.sleep(500L);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        Thread thread2 = new Thread(() -> {
            synchronized (obj) {
                try {
                    Thread.sleep(500L);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        thread1.start();
        Thread.sleep(50L);
        thread2.start();
        Thread.sleep(50L);
        Assertions.assertEquals(Thread.State.BLOCKED, thread2.getState());
    }
}
