package grankin;

/**
 * Класс для демонстрации состояний потока
 */
public class ThreadStates {

    /**
     * Состояние NEW
     * @return "NEW"
     */
    public String testNewThreadState() {
        String result = "";
        Thread thread = new Thread();
        result = thread.getState().toString();
        System.out.println("New thread state: " + thread.getState());
        return result;
    }

    /**
     * Состояние RUNNABLE
     * @return "RUNNABLE"
     */
    public String testRunnableThreadState() {
        String result = "";
        Thread thread = new Thread(() -> {
            ;
        });

        thread.start();
        result = thread.getState().toString();
        System.out.println("Runnable thread state: " + thread.getState());
        return result;
    }

    /**
     * Состояние TERMINATED
     * @return "TERMINATED"
     */
    public String testTerminatedThreadState() throws InterruptedException {
        String result = "";
        Thread thread = new Thread(() -> {
            ;
        });

        thread.start();
        thread.join();
        result = thread.getState().toString();
        System.out.println("Terminated thread state: " + thread.getState());
        return result;
    }

    /**
     * Состояние BLOCKED
     * @return "BLOCKED"
     */
    public String testBlockedThreadState() throws InterruptedException {
        String result = "";
        final Object lock = new Object(); // Объект блокировки

        Thread thread1 = new Thread(() -> {
            synchronized(lock) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            synchronized(lock) {
                ;
            }
        });

        thread1.start();
        thread2.start();

        try {
            // Нужно подождать
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Thread.State state2 = thread2.getState();
        System.out.println("Blocked thread state: " + state2);
        return state2.toString();
    }

    /**
     * Состояние WAITING
     * @return "WAITING"
     */
    public String testWaitingThreadState() throws InterruptedException {
        String result = "";
        Object lock = new Object(); // Объект блокировки

        Thread thread = new Thread(() -> {
            synchronized (lock) {
                try {
                    lock.wait(); // Поток ожидает сигнала о возобновлении
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();

        // Нужно подождать
        Thread.sleep(100); // Позволяем потоку thread начать выполнение

        Thread.State state = thread.getState();
        System.out.println("Waiting thread state: " + state);

        synchronized (lock) {
            lock.notify(); // Возобновляем
        }

        thread.join(); // Ожидание окончания выполнения потока

        return state.toString();
    }

    /**
     * Состояние TIMED_WAITING
     * @return "TIMED_WAITING"
     */
    public String testTimedWaitingThreadState() throws InterruptedException {
        String result = "";
        final Object lock = new Object(); // Объект блокировки

        Thread thread1 = new Thread(() -> {
            synchronized(lock) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            synchronized(lock) {
                ;
            }
        });

        thread1.start();
        thread2.start();

        try {
            // Нужно подождать
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Thread.State state1 = thread1.getState();
        System.out.println("Timed_waiting thread state: " + state1);
        return state1.toString();
    }
}
