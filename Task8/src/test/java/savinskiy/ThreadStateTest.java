package savinskiy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ThreadStateTest {

    private Thread chickenThread(Object lock, int count) {
        return new Thread(() -> {
            for (int i = 0; i < count; i++) {
                synchronized (lock) {
                    System.out.println("Thread 1. Chicken #" + (2 * i));
                    lock.notify();
                    try {
                        if (i < count - 1) {
                            lock.wait();
                        }
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e.getMessage());
                    }
                }
            }
        });
    }

    private Thread eggThread(Object lock, int count) {
        return new Thread(() -> {
            for (int i = 0; i < count; i++) {
                synchronized (lock) {
                    System.out.println("Thread 2. Egg     #" + (2 * i + 1));
                    lock.notify();
                    try {
                        if (i < count - 1) {
                            lock.wait();
                        }
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e.getMessage());
                    }
                }
            }
        });
    }


    @Test
    @DisplayName("Состояния NEW, RUNNABLE, TERMINATED")
    public void testThreadNew() throws InterruptedException {
        Object lock = new Object();
        int count = 50;

        Thread thread1 = chickenThread(lock, count);
        Thread thread2 = eggThread(lock, count);

        assertEquals(Thread.State.RUNNABLE, Thread.currentThread().getState());

        assertEquals(Thread.State.NEW, thread1.getState());
        assertEquals(Thread.State.NEW, thread2.getState());

        thread1.start();
        thread2.start();

        Thread.sleep(1000);

        thread1.join();
        thread2.join();

        assertEquals(Thread.State.TERMINATED, thread1.getState());
        assertEquals(Thread.State.TERMINATED, thread2.getState());
    }

    @Test
    @DisplayName("Состояния NEW, TIMED_WAITING, TERMINATED")
    public void testThreadTmeWaiting() throws InterruptedException {
        Thread thread3 = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e.getMessage());
            }
        });

        assertEquals(Thread.State.NEW, thread3.getState());
        thread3.start();

        Thread.sleep(100);
        assertEquals(Thread.State.TIMED_WAITING, thread3.getState());

        thread3.join();
        assertEquals(Thread.State.TERMINATED, thread3.getState());
    }

    @Test
    @DisplayName("Состояния NEW, WAITING, TERMINATED")
    public void testThreaWaiting() throws InterruptedException {
        Object lock = new Object();

        Thread thread4 = new Thread(() -> {
            synchronized (lock) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e.getMessage());
                }
            }
        });

        assertEquals(Thread.State.NEW, thread4.getState());
        thread4.start();

        Thread.sleep(100);
        assertEquals(Thread.State.WAITING, thread4.getState());

        synchronized (lock) {
            lock.notify();
        }

        thread4.join();
        assertEquals(Thread.State.TERMINATED, thread4.getState());
    }

    @Test
    @DisplayName("Состояния NEW, BLOCKED")
    public void testThreadBlocked() throws InterruptedException {
        Object lock1 = new Object();
        Object lock2 = new Object();

        Thread thread5 = new Thread(() -> {
            synchronized (lock1) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e.getMessage());
                }
                synchronized (lock2) {}
            }
        });

        Thread thread6 = new Thread(() -> {
            synchronized (lock2) {
                synchronized (lock1) {}
            }
        });

        assertEquals(Thread.State.NEW, thread5.getState());
        assertEquals(Thread.State.NEW, thread6.getState());

        thread5.start();
        thread6.start();

        Thread.sleep(100);

        assertEquals(Thread.State.BLOCKED, thread5.getState());
        assertEquals(Thread.State.BLOCKED, thread6.getState());
    }

    @Test
    @DisplayName("Методы yield, notifyAll, sleep")
    public void testThreadNotifyAll() throws InterruptedException {
        Object lock = new Object();
        int count = 10;

        Thread thread7 = chickenThread(lock, count);
        Thread thread8 = eggThread(lock, count);

        assertEquals(Thread.State.NEW, thread7.getState());
        assertEquals(Thread.State.NEW, thread8.getState());

        thread7.start();
        thread8.start();

        Thread.sleep(1000);

        synchronized (lock) {
            lock.notifyAll();
        }

        Thread.yield();

        Thread.sleep(100);

        assertEquals(Thread.State.TERMINATED, thread7.getState());
        assertEquals(Thread.State.TERMINATED, thread8.getState());
    }

}