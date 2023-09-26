package druzhinin;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Класс тестов для получения состояний всех возможных состояний потоков {@link Thread}.
 */
public class ThreadStatesTest {
    /**
     * Объект, по которому будет осуществляться синхронизация.
     */
    private final Object lock = new Object();

    /**
     * Runnable, который будет использоваться для потоков {@link Thread},
     * в методе {@code run} которых не требуется сложная логика.
     */
    private final Runnable simpleRunnable = () -> System.out.println("RUN");

    /**
     * Runnable, использующийся для отлавливания состояния {@code WAITING} у потоков.
     */
    private final Runnable waitRunnable = () -> {
        synchronized (lock) {
            try {
                lock.wait();

                // Выполнять поток некоторое время, чтобы можно было отловить состояние RUNNABLE.
                long startTime = System.currentTimeMillis();
                while (System.currentTimeMillis() - startTime < 2000) {
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    };

    @Test
    @DisplayName("Тест на успешное получение состояния NEW у потока")
    void testSuccessfulGettingNewStateOfThread() {
        Assertions.assertEquals(Thread.State.NEW, new Thread(simpleRunnable).getState());
    }

    @Test
    @DisplayName("Тест на успешное получение состояния RUNNABLE у потока")
    void testSuccessfulGettingRunnableStateOfThread() {
        Thread thread = new Thread(simpleRunnable);
        thread.start();
        Assertions.assertEquals(Thread.State.RUNNABLE, thread.getState());
    }

    @Test
    @DisplayName("Тест на успешное получение состояния RUNNABLE у потока " +
            "через вызов метода notify для вывода потока из состояния WAITING")
    void testSuccessfulGettingRunnableStateOfThreadUsingNotifyMethod() throws InterruptedException {
        Thread waitingThread = new Thread(waitRunnable);

        Thread notifyingThread = new Thread(() -> {
            synchronized (lock) {
                lock.notify();
            }
        });

        waitingThread.start();
        notifyingThread.start();
        Thread.sleep(1000);

        Assertions.assertEquals(Thread.State.RUNNABLE, waitingThread.getState());
    }

    @Test
    @DisplayName("Тест на успешное получение состояния RUNNABLE у двух потоков " +
            "через вызов метода notifyAll для вывода потоков из состояния WAITING")
    void testSuccessfulGettingRunnableStateOfThreadUsingNotifyAllMethod() throws InterruptedException {
        Thread waitingThread1 = new Thread(waitRunnable);
        Thread waitingThread2 = new Thread(waitRunnable);

        Thread notifyingThread = new Thread(() -> {
            synchronized (lock) {
                lock.notifyAll();
            }
        });

        waitingThread1.start();
        waitingThread2.start();
        notifyingThread.start();
        Thread.sleep(1000);


        Assertions.assertEquals(Thread.State.RUNNABLE, waitingThread1.getState());

        // Ждём пока первый поток выйдет из synchronized-блока, чтобы второй поток вышел из состояния BLOCKED.
        while (waitingThread1.getState() != Thread.State.TERMINATED) {
        }
        Assertions.assertEquals(Thread.State.RUNNABLE, waitingThread2.getState());
    }

    @Test
    @DisplayName("Тест на успешное получение состояния RUNNABLE у потока " +
            "после истечения времени у метода wait")
    void testSuccessfulGettingRunnableStateOfThreadAfterTimedWaitingIntervalExpires() throws InterruptedException {
        Thread waitingThread = new Thread(() -> {
            synchronized (lock) {
                try {
                    lock.wait(2000);

                    // Выполнять поток некоторое время, чтобы можно было отловить состояние RUNNABLE.
                    long startTime = System.currentTimeMillis();
                    while (System.currentTimeMillis() - startTime < 2000) {
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        waitingThread.start();

        Thread.sleep(1000);
        Assertions.assertEquals(Thread.State.TIMED_WAITING, waitingThread.getState());

        Thread.sleep(2000);
        Assertions.assertEquals(Thread.State.RUNNABLE, waitingThread.getState());
    }

    @Test
    @DisplayName("Тест на успешное получения состояния TERMINATED" +
            "у потока после завершения выполнения его метода run")
    void testSuccessfulGettingTerminatedStateOfThread() throws InterruptedException {
        Thread thread = new Thread(simpleRunnable);
        thread.start();
        thread.join();
        Assertions.assertEquals(Thread.State.TERMINATED, thread.getState());
    }


    @Test
    @DisplayName("Тест на успешное получения состояния TIMED_WAITING у потока с использованием метода sleep")
    void testSuccessfulGettingTimedWaitingStateOfThreadUsingSleepMethod() throws InterruptedException {
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        thread.start();
        Thread.sleep(1000);
        Assertions.assertEquals(Thread.State.TIMED_WAITING, thread.getState());
    }

    @Test
    @DisplayName("Тест на успешное получения состояния TIMED_WAITING у потока с использованием метода wait")
    void testSuccessfulGettingTimedWaitingStateOfThreadUsingWaitMethod() throws InterruptedException {
        Thread waitingThread = new Thread(() -> {
            synchronized (lock) {
                try {
                    lock.wait(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        waitingThread.start();

        Thread.sleep(1000);

        Assertions.assertEquals(Thread.State.TIMED_WAITING, waitingThread.getState());
    }

    @Test
    @DisplayName("Тест на успешное получения состояния WAITING у потока")
    void testSuccessfulGettingWaitingStateOfThread() throws InterruptedException {
        Thread waitingThread = new Thread(waitRunnable);

        waitingThread.start();

        Thread.sleep(1000);

        Assertions.assertEquals(Thread.State.WAITING, waitingThread.getState());
    }

    @Test
    @DisplayName("Тест на успешное получения состояния BLOCKED у потока")
    void testSuccessfulGettingBlockedStateOfThread() throws InterruptedException {
        Thread runningThread = new Thread(() -> {
            synchronized (lock) {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Thread blockedThread = new Thread(runningThread);

        runningThread.start();
        Thread.sleep(1000);
        blockedThread.start();
        Thread.sleep(1000);

        Assertions.assertEquals(Thread.State.BLOCKED, blockedThread.getState());
    }
}
