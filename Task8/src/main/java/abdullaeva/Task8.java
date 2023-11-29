package abdullaeva;

/**
 * Главный класс для вызова методов получения всех возможных состояний потоков Thread
 */
public class Task8 {
    public static void main(String[] args) throws InterruptedException {
        String allStates;
        AllThreadStates allThreadStates = new AllThreadStates();
        allStates = allThreadStates.threadNewStateTest();
        System.out.println(allStates);
        allStates = allThreadStates.threadRunnableStateTest();
        System.out.println(allStates);
        allStates = allThreadStates.threadWaitingStateTest();
        System.out.println(allStates);
        allStates = allThreadStates.threadTerminatedStateTest();
        System.out.println(allStates);
        allStates = allThreadStates.threadTimedWaitingStateTest();
        System.out.println(allStates);
        allStates = allThreadStates.threadBlockedStateTest();
        System.out.println(allStates);
    }

}
