package grankin;

public class Task8 {
    public static void main(String[] args) throws InterruptedException {
        ThreadStates mytest = new ThreadStates();
        mytest.testNewThreadState();
        mytest.testRunnableThreadState();
        mytest.testTerminatedThreadState();
        mytest.testBlockedThreadState();
        mytest.testWaitingThreadState();
        mytest.testTimedWaitingThreadState();
    }
}
