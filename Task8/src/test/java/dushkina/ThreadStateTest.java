package dushkina;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ThreadStateTest {
    @Test
    @DisplayName("Проверка получения состояния NEW")
    void threadStateNewTest() {
        Thread thread = new Thread();
        Assertions.assertEquals("NEW", thread.getState().toString());
    }

    @Test
    @DisplayName("Проверка получения состояния RUNNABLE")
    void threadStatesRunnableTest() {
        Thread thread = new Thread();
        thread.start();
        Assertions.assertEquals("RUNNABLE", thread.getState().toString());
    }

    @Test
    @DisplayName("Проверка получения состояния WAITING")
    void threadStatesWaitingTest() throws InterruptedException {
        Runnable MyRunnable = () -> {
            synchronized (ThreadStateTest.class) {
                try {
                    ThreadStateTest.class.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Thread thread = new Thread(MyRunnable);
        thread.start();
        Thread.sleep(100);
        Assertions.assertEquals("WAITING", thread.getState().toString());
        synchronized (ThreadStateTest.class) {
            ThreadStateTest.class.notify();
        }
    }

    @Test
    @DisplayName("Проверка получения состояния TIMED_WAITING")
    void threadStatesTimedWaitingTest() throws InterruptedException {
        Runnable MyRunnable = () -> {
            synchronized (ThreadStateTest.class) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Thread thread = new Thread(MyRunnable);
        thread.start();
        Thread.sleep(100);
        Assertions.assertEquals("TIMED_WAITING", thread.getState().toString());
        synchronized (ThreadStateTest.class) {
            ThreadStateTest.class.notify();
        }
    }

    @Test
    @DisplayName("Проверка получения состояния TERMINATED")
    void threadStatesTerminatedTest() throws InterruptedException {
        Thread thread = new Thread();
        thread.start();
        thread.join();
        Assertions.assertEquals("TERMINATED", thread.getState().toString());
    }

    @Test
    @DisplayName("Проверка получения состояния BLOCKED")
    void threadStatesBlockedTest() throws InterruptedException {
        Runnable MyRunnable1 = () -> {
            synchronized (ThreadStateTest.class) {
                try {
                    Thread.sleep(2000);
                    ThreadStateTest.class.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Runnable MyRunnable2 = () -> {
            synchronized (ThreadStateTest.class) {
                ThreadStateTest.class.notify();
            }
        };
        Thread thread1 = new Thread(MyRunnable1);
        Thread thread2 = new Thread(MyRunnable2);
        thread1.start();
        thread2.start();
        Thread.sleep(1000);
        Assertions.assertEquals("BLOCKED", thread2.getState().toString());
    }
}