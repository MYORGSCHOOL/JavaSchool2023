package savinskiy;

public class Task5 {
    public static void main(String[] args) {
        Dictionary<Integer> dictionary = new DictionaryImpl<>();

        long startInsertTime = System.nanoTime();
        for (int i = 0; i < 1_000_000; i++) {
            dictionary.insert(i);
        }
        long endInsertTime = System.nanoTime();
        System.out.println("Time for 'insert' method: " + ((endInsertTime - startInsertTime) / 1_000_000));

        long startGetRandomTime = System.nanoTime();
        for (int i = 0; i < 1_000_000; i++) {
            dictionary.getRandom();
        }
        long endGetRandomTime = System.nanoTime();
        System.out.println("Time for 'getRandom' method: " + (endGetRandomTime - startGetRandomTime) / 1_000_000);

        long startConsistTime = System.nanoTime();
        for (int i = 0; i < 1_000_000; i++) {
            dictionary.delete(i);
        }
        long endConsistTime = System.nanoTime();
        System.out.println("Time for 'consist' method: " + (endConsistTime - startConsistTime) / 1_000_000);

        long startDeleteTime = System.nanoTime();
        for (int i = 0; i < 1_000_000; i++) {
            dictionary.delete(i);
        }
        long endDeleteTime = System.nanoTime();
        System.out.println("Time for 'delete' method: " + (endDeleteTime - startDeleteTime) / 1_000_000);
    }
}
