package alexenko;

import alexenko.exeptions.CatNotHaveBowlException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Класс тест, в котором получаются все состояния потока Cat.
 */
public class ThreadStateTest {

    private static final int COUNT_EAT_IN_BOWL = 5;
    private static final int EMPTY_BOWL = 0;
    private static final long MAIN_THREAD_SLEEP_TIME = 1000L;

    @Test
    @DisplayName("Поток в состоянии NEW")
    public void testThreadInStateNew() {
        Thread cat = new Thread(new Cat());
        Assertions.assertEquals(Thread.State.NEW, cat.getState());
    }

    @Test
    @DisplayName("Поток в состоянии RUNNABLE")
    public void testThreadInStateRunnable() {
        Thread cat = new Thread(new Cat());
        cat.start();
        Assertions.assertEquals(Thread.State.RUNNABLE, cat.getState());
    }

    @Test
    @DisplayName("Поток в состоянии TERMINATE")
    public void testThreadInStateTerminate() throws InterruptedException {
        Thread cat = new Thread(new Cat());
        cat.start();
        cat.join();
        Assertions.assertEquals(Thread.State.TERMINATED, cat.getState());
    }

    @Test
    @DisplayName("Поток в состоянии BLOCKED")
    public void testThreadInStateBlocked() throws InterruptedException {
        Bowl bowl = new Bowl(COUNT_EAT_IN_BOWL);
        Thread cat1 = new Thread(new Cat(bowl));
        Thread cat2 = new Thread(new Cat(bowl));
        cat1.start();
        cat2.start();
        Thread.sleep(MAIN_THREAD_SLEEP_TIME);
        Assertions.assertEquals(Thread.State.BLOCKED, cat2.getState());
    }

    @Test
    @DisplayName("Поток в состоянии TIMED_WAITING")
    public void testThreadInStateTimeWaiting() throws InterruptedException {
        Bowl bowl = new Bowl(COUNT_EAT_IN_BOWL);
        Thread cat = new Thread(new Cat(bowl));
        cat.start();
        Thread.sleep(MAIN_THREAD_SLEEP_TIME);
        Assertions.assertEquals(Thread.State.TIMED_WAITING, cat.getState());
    }

    @Test
    @DisplayName("Поток в состоянии WAITING")
    public void testThreadInStateWaiting() throws InterruptedException {
        Bowl bowl = new Bowl(EMPTY_BOWL);
        Thread cat = new Thread(new Cat(bowl));
        cat.start();
        Thread.sleep(MAIN_THREAD_SLEEP_TIME);
        Assertions.assertEquals(Thread.State.WAITING, cat.getState());
    }
}
