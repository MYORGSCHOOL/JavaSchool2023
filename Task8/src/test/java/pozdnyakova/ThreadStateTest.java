package pozdnyakova;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Тестовый класс для демонстраций состояний потока
 */
public class ThreadStateTest {
    /**
     * Поток для демонстрации состояний
     */
    private Thread thread;
    /**
     * Строка для проверки состояния
     */
    private String state;
    /**
     * Объект, по которому происходит синхронизация
     */
    private final Object synchronizedObject = new Object();

    @Test
    @DisplayName("Получение состояния потока NEW")
    public void testGetStateNew() {
        state = "NEW";
        thread = new Thread();
        Assertions.assertEquals(state, thread.getState().toString());
    }

    @Test
    @DisplayName("Получение состояния потока RUNNABLE")
    public void testGetStateRunnable() {
        state = "RUNNABLE";
        thread = new Thread();
        thread.start();
        Assertions.assertEquals(state, thread.getState().toString());
    }

    @Test
    @DisplayName("Получение состояния потока TERMINATED")
    public void testGetStateTerminated() throws InterruptedException {
        state = "TERMINATED";
        thread = new Thread();
        thread.start();
        thread.join();
        Assertions.assertEquals(state, thread.getState().toString());
    }

    @Test
    @DisplayName("Получение состояния потока WAITING")
    public void testGetStateWaiting() {
        state = "WAITING";
        thread = new Thread(() -> {
            synchronized (synchronizedObject) {
                try {
                    synchronizedObject.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            Assertions.assertEquals(state, thread.getState().toString());
        });
        thread.start();
    }

    @Test
    @DisplayName("Получение состояния потока TIMED_WAITING")
    public void testGetStateTimedWaiting() {
        state = "TIMED_WAITING";
        thread = new Thread(() -> {
            synchronized (synchronizedObject) {
                try {
                    synchronizedObject.wait(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            Assertions.assertEquals(state, thread.getState().toString());
        });
        thread.start();
    }

    @Test
    @DisplayName("Получение состояния потока BLOCKED")
    public void testGetStateBlocked() {
        state = "BLOCKED";
        Thread addThread = new Thread(() -> {
            synchronized (synchronizedObject) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        addThread.start();
        thread = new Thread(() -> {
            synchronized (synchronizedObject) {
                System.out.println("No blocked");
            }
        });
        thread.start();
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Assertions.assertEquals(state, thread.getState().toString());
    }
}
