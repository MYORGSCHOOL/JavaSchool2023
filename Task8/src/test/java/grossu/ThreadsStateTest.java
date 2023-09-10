package grossu;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Класс для отлавливания всех состояний потока и проверки подлинности состояний
 */
public class ThreadsStateTest {
    /**
     * Поток для отлавливания waiting состояния
     */
    private static Thread waitingThread;
    /**
     * Переменная для получения new, runnable, terminated состояний потока
     */
    private static Runnable runnable;

    /**
     * Класс для получения Blocked состояния потока
     */
    static class BlockedState implements Runnable {

        @Override
        public void run() {
            createBlockedThreadState();
        }

        /**
         * Метод для создания состояния ожидания в потоке за счет бесконечного цикла
         * Так как метод synchronized, то только один поток сможет с ним работать,
         * а бесконечный цикл гарантирует, что другой поток не сможет обратится к методу,
         * так как поток который первый его заполучит будет выполнять бесконечный цикал
         */
        public static synchronized void createBlockedThreadState() {
            while (true) {
            }
        }
    }

    @BeforeAll
    static void init() {
        runnable = () -> {
        };
    }

    @Test
    @DisplayName("Catch new thread's state")
    void testCatchNewThreadsState() {
        Thread actual = new Thread(runnable);
        String expected = "NEW";
        Assertions.assertEquals(expected, actual.getState().toString());
    }

    @Test
    @DisplayName("Catch runnable thread's state")
    void testCatchRunnableThreadsState() {
        Thread actual = new Thread(runnable);
        actual.start();
        String expected = "RUNNABLE";
        Assertions.assertEquals(expected, actual.getState().toString());
    }

    /**
     * Тест в котором проверяется получения Blocked состояния потока.
     * Для этого создаются два потока c заданным интерфейсом {@code BlockedState}
     * Каждый поток запускается, а затем поток усыпляется, получается ситуация,
     * что поток {@code t2} ожидает когда будет доступен метод {@code createBlockedThreadState}
     * занятый птоком {@code t1}, в результате {@code t2} - будет в состоянии Blocked
     */
    @Test
    @DisplayName("Catch blocked thread's state")
    void testCatchBlockedThreadsState() {
        Thread t1 = new Thread(new BlockedState());
        Thread t2 = new Thread(new BlockedState());
        t1.start();
        t2.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println(e.getMessage());
        }
        String expected = "BLOCKED";
        Assertions.assertEquals(expected, t2.getState().toString());
    }

    /**
     * Тест для проверки получения waiting состояния потока
     * поток {@code waitingThread} создает поток {@code workedThread},
     * который запускает метод join и  переводит поток {@code waitingThrea} в состояние ожидания
     */
    @Test
    @DisplayName("Catch waiting thread's state")
    void testCatchWaitingThreadsState() {
        Runnable waitingRunnable = () -> {
            Runnable waiteStateThread = () -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();

                    System.out.println(e.getMessage());
                }
                String expected = "WAITING";
                Assertions.assertEquals(expected, waitingThread.getState().toString());
            };

            Thread workedThread = new Thread(waiteStateThread);
            workedThread.start();
            try {
                workedThread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println(e.getMessage());
            }

        };
        waitingThread = new Thread(waitingRunnable);
        waitingThread.start();

    }

    /**
     * Тест для получения состояния time_waiting,
     * за счетов slepp-ов потоку достаточно времени на выполнение задач,
     * поэтому удается отловить его в состояние time_waiting
     */
    @Test
    @DisplayName("Catch time_waiting thread's state")
    void testCatchTimeWaitingState() {
        Runnable timeWait = () -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println(e.getMessage());
            }
        };
        Thread actual = new Thread(timeWait);
        actual.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        String expected = "TIMED_WAITING";
        Assertions.assertEquals(expected, actual.getState().toString());
    }

    /**
     * Тест для получения состояния terminate,
     * за счетов slepp-а потоку достаточно времени на выполнение задач,
     * поэтому удается отловить его в состояние terminated
     */
    @Test
    @DisplayName("Catch terminated thread's state")
    void testCatchTerminatedThreadsState() {
        Thread actual = new Thread(runnable);
        actual.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println(e.getMessage());
        }
        Assertions.assertFalse(actual.isAlive());
    }


}

