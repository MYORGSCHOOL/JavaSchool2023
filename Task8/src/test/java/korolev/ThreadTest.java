package korolev;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ThreadTest {
    @Test
    @DisplayName("Test for creating a new thread.")
    public void testNewThread() {
        Thread thread = new Thread();
        Assertions.assertEquals(Thread.State.NEW, thread.getState());
    }

    @Test
    @DisplayName("Test for correct start of the flow.")
    public void TestStartThread() {
        Thread thread = new Thread();
        thread.start();
        Assertions.assertEquals(Thread.State.RUNNABLE, thread.getState());
    }

    @Test
    @DisplayName("Test for correct thread termination.")
    public void testStopThread() throws InterruptedException {
        Thread thread = new Thread();
        thread.start();
        thread.join();
        Assertions.assertEquals(Thread.State.TERMINATED, thread.getState());
    }

    @Test
    @DisplayName("Test for correct thread suspension.")
    public void testWaitThread() throws InterruptedException {
        Runnable task = () -> {
            try {
                wait(10000);
            } catch (Exception e) {
                System.out.println(e);
            }
        };

        Thread thread = new Thread(task);
        
        try {
            thread.start();
            thread.wait(10000);
            Assertions.assertEquals(Thread.State.TIMED_WAITING, thread.getState());
        notify();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Test
    @DisplayName("Test for correct thread blocking.")
    public void testBlockedThread() throws InterruptedException {
        Object obj = new Object();
        Runnable task = () -> {
            try {
                synchronized (obj) {
                    obj.wait();
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        };

        Thread thread = new Thread(task);
        thread.start();

        try {
            synchronized (obj) {
                Thread.sleep(5000);
                Assertions.assertEquals(Thread.State.BLOCKED, thread.getState());
                notify();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
