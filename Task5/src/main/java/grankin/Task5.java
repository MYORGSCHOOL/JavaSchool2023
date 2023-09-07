package grankin;

public class Task5 {

    public static void main(String[] args) {
        DictionaryImpl<Integer> dictionary = new DictionaryImpl<>();

        long startTime = 0;
        long endTime = 0;
        long timeElapsed = 0;

        int countIteration = 10_000_000;    // 10 млн вроде работает за 0.6 секунды

        int forDivisionNanoSecToSec = 1000000000;

        startTime = System.nanoTime();
        for (int i = 0; i < countIteration; i++) {
            dictionary.insert(i);
        }
        endTime = System.nanoTime();
        timeElapsed = endTime - startTime;
        System.out.println("Insert count = " + countIteration + ", time = " +
                timeElapsed / (double) forDivisionNanoSecToSec + " sec");

        startTime = System.nanoTime();
        for (int i = 0; i < countIteration; i++) {
            dictionary.consist(i);
        }
        endTime = System.nanoTime();
        timeElapsed = endTime - startTime;
        System.out.println("Consist count = " + countIteration + ", time = " +
                timeElapsed / (double) forDivisionNanoSecToSec + " sec");

        startTime = System.nanoTime();
        for (int i = 0; i < countIteration; i++) {
            dictionary.getRandom();
        }
        endTime = System.nanoTime();
        timeElapsed = endTime - startTime;
        System.out.println("getRandom count = " + countIteration + ", time = " +
                timeElapsed / (double) forDivisionNanoSecToSec + " sec");

        startTime = System.nanoTime();
        for (int i = 0; i < countIteration; i++) {
            dictionary.delete(i);
        }
        endTime = System.nanoTime();
        timeElapsed = endTime - startTime;
        System.out.println("Delete count = " + countIteration + ", time = " +
                timeElapsed / (double) forDivisionNanoSecToSec + " sec");

        System.out.println();
        System.out.println("Test insert 10 and delete 5 and display INFO");

        for (int i = 0; i < 10; i++) {
            dictionary.insert(i);
        }
        for (int i = 0; i < 5; i++) {
            dictionary.delete(i);
        }
        System.out.println("Current size = " + dictionary.sizeDictionary());
    }
}
