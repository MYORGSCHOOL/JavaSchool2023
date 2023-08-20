package skrebkov;

public class Task5 {
    private static final int DICTIONARY_SIZE_FOR_TESTS = 1_000_000;

    /**
     * Проверяю время выполнения методов при разной заполненности словаря
     */
    public static void main(String[] args) {
        var dictionary = new DictionaryImpl<String>();
        var testElement = "Тест";

        long startTime = System.currentTimeMillis();
        dictionary.insert(testElement);
        long endTime = System.currentTimeMillis();
        long emptyDictionaryInsertTime = endTime - startTime;

        startTime = System.currentTimeMillis();
        dictionary.consist(testElement);
        endTime = System.currentTimeMillis();
        long emptyDictionaryConsistTime = endTime - startTime;

        startTime = System.currentTimeMillis();
        dictionary.getRandom();
        endTime = System.currentTimeMillis();
        long emptyDictionaryRandomTime = endTime - startTime;

        startTime = System.currentTimeMillis();
        dictionary.delete(testElement);
        endTime = System.currentTimeMillis();
        long emptyDictionaryDeleteTime = endTime - startTime;

        for (int i = 0; i < DICTIONARY_SIZE_FOR_TESTS; i++) {
            dictionary.insert(Integer.toString(i));
        }

        startTime = System.currentTimeMillis();
        dictionary.insert(testElement);
        endTime = System.currentTimeMillis();
        long fullDictionaryInsertTime = endTime - startTime;

        startTime = System.currentTimeMillis();
        dictionary.consist(testElement);
        endTime = System.currentTimeMillis();
        long fullDictionaryConsistTime = endTime - startTime;

        startTime = System.currentTimeMillis();
        dictionary.getRandom();
        endTime = System.currentTimeMillis();
        long fullDictionaryRandomTime = endTime - startTime;

        startTime = System.currentTimeMillis();
        dictionary.delete(testElement);
        endTime = System.currentTimeMillis();
        long fullDictionaryDeleteTime = endTime - startTime;

        System.out.println("Время выполнения вставки в словарь пустой=" + emptyDictionaryInsertTime
                + ", заполненный=" + fullDictionaryInsertTime);
        System.out.println("Время выполнения поиска в словаре пустой=" + emptyDictionaryConsistTime
                + ", заполненный=" + fullDictionaryConsistTime);
        System.out.println("Время выполнения выдачи случайного элемента из словаря пустой=" + emptyDictionaryRandomTime
                + ", заполненный=" + fullDictionaryRandomTime);
        System.out.println("Время выполнения удаления из словаря пустой=" + emptyDictionaryDeleteTime
                + ", заполненный=" + fullDictionaryDeleteTime);

        startTime = System.currentTimeMillis();
        for (int i = 0; i < DICTIONARY_SIZE_FOR_TESTS; i++) {
            dictionary.insert(i + testElement);
        }
        endTime = System.currentTimeMillis();
        System.out.println("Время вставки " + DICTIONARY_SIZE_FOR_TESTS + " составляет " + (endTime - startTime));

        startTime = System.currentTimeMillis();
        for (int i = 0; i < DICTIONARY_SIZE_FOR_TESTS; i++) {
            dictionary.consist(i + testElement);
        }
        endTime = System.currentTimeMillis();
        System.out.println("Время поиска " + DICTIONARY_SIZE_FOR_TESTS + " составляет " + (endTime - startTime));

        startTime = System.currentTimeMillis();
        for (int i = 0; i < DICTIONARY_SIZE_FOR_TESTS; i++) {
            dictionary.getRandom();
        }
        endTime = System.currentTimeMillis();
        System.out.println("Время взятия рандомного элемента " + DICTIONARY_SIZE_FOR_TESTS + " составляет " + (endTime - startTime));

        startTime = System.currentTimeMillis();
        for (int i = 0; i < DICTIONARY_SIZE_FOR_TESTS; i++) {
            dictionary.delete(i + testElement);
        }
        endTime = System.currentTimeMillis();
        System.out.println("Время удаления " + DICTIONARY_SIZE_FOR_TESTS + " составляет " + (endTime - startTime));

    }
}
