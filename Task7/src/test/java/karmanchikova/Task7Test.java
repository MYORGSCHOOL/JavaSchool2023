package karmanchikova;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Task7Test {
    private final Object object = new Object();

    @Test
    @DisplayName("Тест на успешное создание нового потока")
    public void testSuccessCreateNewThread() {
        Thread thread = new Thread();
        Assertions.assertEquals(Thread.State.NEW, thread.getState());
    }

    @Test
    @DisplayName("Тест на успешое выполнение потока")
    public void testSuccessExecutionThread() {
        Thread thread = new Thread();
        thread.start();
        Assertions.assertEquals(Thread.State.RUNNABLE, thread.getState());
    }

    @Test
    @DisplayName("Тест на успешное завершение выполнения потока")
    public void testSuccessEndThread() throws InterruptedException {
        Thread thread = new Thread();
        thread.start();
        thread.join();
        Assertions.assertEquals(Thread.State.TERMINATED, thread.getState());
    }

    @Test
    @DisplayName("Тест на успешное завершение выполнения потока")
    public void testSuccessStopThread() throws InterruptedException {
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
    @DisplayName("Тест на успешное завершение выполнения потока")
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