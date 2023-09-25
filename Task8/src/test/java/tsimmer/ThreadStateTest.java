package tsimmer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Класс для демонстрации состояний потока
 */
public class ThreadStateTest {
    /**
     * Поток
     */
    private Thread thread;
    /**
     * Объект для синхронизации
     */
    private final Object object = new Object();

    @Test
    @DisplayName("Получение состояния потока New")
    void testSuccessCreateThread() {
        thread = new Thread();
        Assertions.assertEquals(Thread.State.NEW, thread.getState());
    }

    @Test
    @DisplayName("Получение состояния потока Runnable")
    void testSuccessStartThread() {
        thread = new Thread(() -> System.out.println("Runnable"));
        thread.start();
        Assertions.assertEquals(Thread.State.RUNNABLE, thread.getState());
    }

    @Test
    @DisplayName("Получение состояния потока Terminated")
    void testStateStreamTerminated() throws InterruptedException {
        thread = new Thread(() -> System.out.println("running"));
        thread.start();
        thread.join();
        Assertions.assertEquals(Thread.State.TERMINATED, thread.getState());
    }

    @Test
    @Disabled
    @DisplayName("Перход в состояние runnable с помощью метода notify")
    void testStateStreamRunnable() throws InterruptedException {
        thread = new Thread(() -> {
            synchronized (object) {
                try {
                    object.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        thread.start();
        Thread.sleep(1000);
        synchronized (object) {
            object.notify();
        }
        Assertions.assertEquals(Thread.State.RUNNABLE, thread.getState());
    }

    @Test
    @DisplayName("Получение состояния потока Waiting")
    void testStateStreamWaiting() throws InterruptedException {
        thread = new Thread(() -> {
            synchronized (object) {
                try {
                    object.wait();
                    Assertions.assertEquals(Thread.State.WAITING,thread.getState());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        thread.start();
        Thread.sleep(1000);
        Assertions.assertEquals(Thread.State.WAITING, thread.getState());

    }

    @Test
    @DisplayName("Получение состояния потока Timed Waiting")
    void testStateStreamTimeWaiting() throws InterruptedException {
        thread = new Thread(() -> {
            synchronized (object) {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        thread.start();
        Thread.sleep(100);
        Assertions.assertEquals(Thread.State.TIMED_WAITING, thread.getState());
    }

    @Test
    @DisplayName("Получение состояния потока Blocked")
    void testStateStreamBlocked() throws InterruptedException {
        thread = new Thread(() -> {
            synchronized (object) {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        Thread thread1 = new Thread(() -> {
            synchronized (object) {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        thread.start();
        Thread.sleep(10);
        thread1.start();
        Thread.sleep(10);
        Assertions.assertEquals(Thread.State.BLOCKED, thread1.getState());
    }
}
