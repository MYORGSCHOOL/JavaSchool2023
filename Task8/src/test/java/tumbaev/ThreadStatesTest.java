package tumbaev;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ThreadStatesTest {

    @Test
    void testNewState() {
        Thread thread = new Thread();
        assertEquals(Thread.State.NEW, thread.getState(), "State should be NEW");
    }

    @Test
    void testRunnableState() {
        Thread thread = new Thread(() -> System.out.println("I am running"));
        thread.start();
        assertEquals(Thread.State.RUNNABLE, thread.getState(), "State should be RUNNABLE");
    }

    @Test
    void testToWaitingStateAndBackToRunnable() throws InterruptedException {
        final Object lock = new Object();
        Thread thread = new Thread(() -> {
            System.out.println("I am running and I will wait soon...");
            synchronized (lock) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println("I am running again");

            //run the thread for some time to catch it in a RUNNABLE state before it terminates
            long startTime = System.currentTimeMillis();
            while (System.currentTimeMillis() - startTime < 5000) {
            }
        });
        thread.start();

        Thread.sleep(1000); //give the thread time to become WAITING
        assertEquals(Thread.State.WAITING, thread.getState(),
                "Thread should be in WAITING state after lock.wait()");

        synchronized (lock) {
            lock.notify();
        }

        Thread.sleep(1000); //give the thread time to become RUNNABLE
        assertEquals(Thread.State.RUNNABLE, thread.getState(),
                "Thread should be in RUNNABLE state after lock.notify()");
    }

    @Test
    void testToTimedWaitingStateAndBackToRunnable() throws InterruptedException {
        Thread thread = new Thread(() -> {
            System.out.println("I am running and I will sleep soon...");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("I am running again");
            //run the thread for some time to catch it in a RUNNABLE state before it terminates
            long startTime = System.currentTimeMillis();
            while (System.currentTimeMillis() - startTime < 5000) {
            }
        });
        thread.start();

        Thread.sleep(1000); //give the thread time to become TIMED_WAITING
        assertEquals(Thread.State.TIMED_WAITING, thread.getState(),
                "State should be TIMED_WAITING after Thread.sleep()");


        Thread.sleep(3000); //give the thread time to become RUNNABLE
        assertEquals(Thread.State.RUNNABLE, thread.getState(),
                "State should be RUNNABLE when Thread.sleep() is finished");
    }

    @Test
    void testToBlockedStateAndBackToRunnable() throws InterruptedException {
        final Object lock = new Object();
        Thread thread1 = new Thread(() -> {
            synchronized (lock) {
                long startTime = System.currentTimeMillis();
                while (System.currentTimeMillis() - startTime < 5000) {
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            System.out.println("Thread2 is near the synchronized block");
            synchronized (lock) {
                System.out.println("Thread 2 entered the synchronized block");
            }
            long startTime = System.currentTimeMillis();
            while (System.currentTimeMillis() - startTime < 5000) {
            }
        });

        thread1.start();
        thread2.start();

        Thread.sleep(1000); //give the thread time to become BLOCKED
        assertEquals(Thread.State.BLOCKED, thread2.getState(), "Thread2 should be in BLOCKED state");

        Thread.sleep(5000); //give the thread time to become RUNNABLE
        assertEquals(Thread.State.RUNNABLE, thread2.getState(), "Thread2 should be in RUNNABLE state");
    }

    @Test
    void testTerminated() throws InterruptedException {
        Thread thread = new Thread(() -> System.out.println("I'm gonna die in a second :)"));
        thread.start();
        assertEquals(Thread.State.RUNNABLE, thread.getState(), "Thread should be in RUNNABLE state");
        Thread.sleep(1000); //give the to terminate
        assertEquals(Thread.State.TERMINATED, thread.getState(), "Thread should be in TERMINATED state");
    }
}
