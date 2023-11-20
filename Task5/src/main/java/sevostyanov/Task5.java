package sevostyanov;

public class Task5 {
    public static void main(String[] args) {

        Dictionary<Integer> dictionary = new DictionaryImpl<>();
        dictionary.insert(1);
        dictionary.delete(1);
        dictionary.insert(4);
        dictionary.insert(3);
        dictionary.insert(1);
        System.out.println(dictionary);
        long startGetRandomTime = System.nanoTime();
        for (int i = 0; i < 1_000_000; i++) {
            dictionary.getRandom();
        }
        long endGetRandomTime = System.nanoTime();
        System.out.println("Время метода getRandom " + (endGetRandomTime - startGetRandomTime) / 1_000_000);

        long startConsistTime = System.nanoTime();
        for (int i = 0; i < 1_000_000; i++) {
            dictionary.consist(i);
        }
        long endConsistTime = System.nanoTime();
        System.out.println("Время выполнения метода consist " + (endConsistTime - startConsistTime) / 1_000_000);
        long startDeletedTime = System.nanoTime();
        for (int i = 0; i < 1_000_000; i++) {
            dictionary.delete(i);
        }
        long endDeletedTime = System.nanoTime();
        System.out.println("Время выполнения метода delete " + ((endDeletedTime - startDeletedTime) / 1_000_000));
    }
}

