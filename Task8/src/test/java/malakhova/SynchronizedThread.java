package malakhova;

/**
 * Класс, содержащий методы для синхронизации потоков
 */
public class SynchronizedThread {
    public static synchronized void blockedState() throws InterruptedException {
        Thread.sleep(3000);
    }

    class WaitingThread implements Runnable {
        public SynchronizedThread thread = new SynchronizedThread();

        @Override
        public void run() {
            try {
                thread.waitingState();
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getState());
            }
        }

    }

    public synchronized void waitingState() throws InterruptedException {
        wait();
    }
}
