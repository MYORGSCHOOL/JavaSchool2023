package proskurina;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Класс, который демонстрирует все возможные состояния {@link Thread}.
 */
class ThreadStatesTest {
    
    private Thread thread;
    private final Object mutex = new Object();
    
    @Test
    @Disabled
    @DisplayName("Тестируется состояние NEW")
    void testNewState() {
        thread = new Thread();
        
        Assertions.assertEquals(Thread.State.NEW, thread.getState());
    }
    
    @Test
    @Disabled
    @DisplayName("Тестируется состояние RUNNABLE")
    void testRunnableState() {
        thread = new Thread();
        thread.start();
        
        Assertions.assertEquals(Thread.State.RUNNABLE, thread.getState());
    }
    
    @Test
    @Disabled
    @DisplayName("Тестируется состояние BLOCKED")
    void testBlockedState() throws InterruptedException {
        thread = new Thread(() -> {
            synchronized (mutex) {
            }
        });
        
        synchronized (mutex) {
            thread.start();
            Thread.sleep(1L); // Ждем перехода от RUNNABLE к BLOCKED
            Assertions.assertEquals(Thread.State.BLOCKED, thread.getState());
        }
    }
    
    @Test
    @Disabled
    @DisplayName("Тестируется состояние WAITING")
    void testWaitingState() throws InterruptedException {
        thread = new Thread(() -> {
            synchronized (mutex) {
                try {
                    mutex.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        
        thread.start();
        Thread.sleep(1L); // Ждем перехода от RUNNABLE к WAITING
        
        Assertions.assertEquals(Thread.State.WAITING, thread.getState());
        synchronized (mutex) {
            mutex.notify();
        }
    }
    
    @Test
    @Disabled
    @DisplayName("Тестируется состояние TIMED_WAITING")
    void testTimedWaitingState() throws InterruptedException {
        thread = new Thread(() -> {
            try {
                Thread.sleep(1_000L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        
        thread.start();
        Thread.sleep(1L); // Ждем перехода от RUNNABLE к TIMED_WAITING
        
        Assertions.assertEquals(Thread.State.TIMED_WAITING, thread.getState());
    }
    
    @Test
    @Disabled
    @DisplayName("Тестируется состояние TERMINATED")
    void testTerminatedState() throws InterruptedException {
        thread = new Thread();
        
        thread.start();
        thread.join();
        
        Assertions.assertEquals(Thread.State.TERMINATED, thread.getState());
    }
    
}
