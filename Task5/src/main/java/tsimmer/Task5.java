package tsimmer;

public class Task5 {
    private static final int QUANTITY_ELEMENTS = 1_000_000;

    public static void main(String[] args) {
        DictionaryImpl<Integer> dictionary = new DictionaryImpl<>();
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < QUANTITY_ELEMENTS; i++) {
            dictionary.insert(i);
        }
        long endTime = System.currentTimeMillis();
        long time = (endTime - startTime);
        System.out.println("Время добавления элементов: " + time);

        long startTimeConsist = System.currentTimeMillis();
        dictionary.consist(99999);
        long endTimeConsist = System.currentTimeMillis();
        long timeConsist = (endTimeConsist - startTimeConsist);
        System.out.println("Время поиска элемента: " + timeConsist);

        long startTimeRandom = System.currentTimeMillis();
        for (int i = 0; i < QUANTITY_ELEMENTS; i++) {
            dictionary.getRandom();
        }
        long endTimeRandom = System.currentTimeMillis();
        long timeRandom = (endTimeRandom - startTimeRandom);
        System.out.println("Время выполнения Random: " + timeRandom);

        long startTimeDelete = System.currentTimeMillis();
        for (int i = 0; i < QUANTITY_ELEMENTS - 1; i++) {
            dictionary.delete(i);
        }
        long endTimeDelete = System.currentTimeMillis();
        long timeDelete = (endTimeDelete - startTimeDelete);
        System.out.println("Время удаления элементов: " + timeDelete);
    }
}
