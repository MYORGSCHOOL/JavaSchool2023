package abdullaeva;

import static java.lang.Thread.sleep;

/**
 * Класс для работы с Thread и получения всех возможных состояний потока
 */
public class AllThreadStates {

    private final Object stubSync = new Object();

    /**
     * Метод для вывода состояния нового созданного потока - NEW
     */
    public String threadNewStateTest() {
        String state = "";
        Thread thread = new Thread();
        state = thread.getState().toString();
        System.out.println("State of the new thread is: ");
        return state;
    }

    /**
     * Метод для вывода состояния после старта потока - RUNNABLE
     */
    public String threadRunnableStateTest() {
        String state = "";
        Thread thread = new Thread(() -> {

        });
        thread.start();
        state = thread.getState().toString();
        System.out.println("State of the running thread is: ");
        return state;
    }

    /**
     * Метод для вывода состояния потока, который ждёт вызова notify() - WAITING
     */
    public String threadWaitingStateTest() throws InterruptedException {
        String state = "";
        Thread thread = new Thread(() -> {
            synchronized (stubSync) {
                try {
                    stubSync.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        thread.start();
        thread.join(100);
        state = thread.getState().toString();
        System.out.println("State of the thread with waiting synchronized object is: ");
        return state;
    }

    /**
     * Метод для вывода состояния завершившегося потока - TERMINATED
     */
    public String threadTerminatedStateTest() throws InterruptedException {
        String state = "";
        Thread thread = new Thread(() -> {

        });
        thread.start();
        thread.join();
        state = thread.getState().toString();
        System.out.println("State of the only thread after join(): ");
        return state;
    }

    /**
     * Метод для вывода состояния потока, находящегося в ограниченном по времени ожидании - TIMED_WAITING
     */
    public String threadTimedWaitingStateTest() throws InterruptedException {
        String state = "";
        Thread thread = new Thread(() -> {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
        });
        thread.start();
        Thread.sleep(250);
        state = thread.getState().toString();
        System.out.println("State of the thread with certain timeout  is: ");
        return state;
    }

    /**
     * Метод для вывода состояния потока, пытающегося получить доступ к синхронизированному блоку,
     * который удерживается другим потоком - BLOCKED
     */
    public String threadBlockedStateTest() throws InterruptedException {
        String state = "";
        Thread thread = new Thread(() -> {
            synchronized (stubSync) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        Thread thread1 = new Thread(() -> {
            synchronized (stubSync) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        thread.start();
        Thread.sleep(20);
        thread1.start();
        Thread.sleep(20);
        state = thread1.getState().toString();
        System.out.println("State of the thread trying to access a synchronized block occupied by another thread is: ");
        return state;
    }


}
