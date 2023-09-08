package yanko;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestThread {
    private final Object object = new Object();

    @Test
    @DisplayName("создает новый поток")
    public void testSuccessCreateThread() {
        Thread thread = new Thread();
        Assertions.assertEquals(Thread.State.NEW, thread.getState());
    }

    @Test
    @DisplayName("запускает поток")
    public void testSuccessRunThread() {
        Thread thread = new Thread();
        thread.start();
        Assertions.assertEquals(Thread.State.RUNNABLE, thread.getState());
    }

    @Test
    @DisplayName("завершает выполнение потока")
    public void testSuccessTerminateThread() throws InterruptedException {
        Thread thread = new Thread();
        thread.start();
        thread.join();
        Assertions.assertEquals(Thread.State.TERMINATED, thread.getState());
    }

    @Test
    @DisplayName("приостанавливает поток")
    public void testSuccessWaitThread() throws InterruptedException {
        Thread thread = new Thread(() -> {
            try {
                synchronized (object) {
                    object.wait();
                }
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        });
        thread.start();
        synchronized (object) {
            object.wait(1000);
            Assertions.assertEquals(Thread.State.WAITING, thread.getState());
            object.notify();
        }
    }

    @Test
    @DisplayName("приостанавливает поток на заданное время")
    public void testSuccessWaitTimedThread() throws InterruptedException {
        Thread thread = new Thread(() -> {
            try {
                synchronized (object) {
                    object.wait(5000);
                }
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }

        });
        thread.start();
        synchronized (object) {
            object.wait(1000);
            Assertions.assertEquals(Thread.State.TIMED_WAITING, thread.getState());
            object.notify();
        }
    }

    @Test
    @DisplayName("блокирует поток")
    public void testSuccessBlockThread() throws InterruptedException {
        Thread thread = new Thread(() -> {
            try {
                synchronized (object) {
                    object.wait();
                }
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        });
        thread.start();
        synchronized (object) {
            Thread.sleep(1000);
            Assertions.assertEquals(Thread.State.BLOCKED, thread.getState());
            object.notify();
        }
    }
}
