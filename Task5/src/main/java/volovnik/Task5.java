package volovnik;

public class Task5 {

    public static void main(String[] args) {
        DictionaryImpl<Integer> dictionary = new DictionaryImpl<>();

        long insertTimeStart = System.currentTimeMillis();
        for (int i = 0; i < 1_000_000; i++) {
            dictionary.insert(i);
        }
        long insertEndTime = System.currentTimeMillis();
        System.out.println("Время вставки: " + (insertEndTime - insertTimeStart) + " мс");

        long consistTimeStart = System.currentTimeMillis();
        for (int i = 0; i < 1_000_000; i++) {
            dictionary.consist(i);
        }
        long consistTimeEnd = System.currentTimeMillis();
        System.out.println("Время поиска: " + (consistTimeEnd - consistTimeStart) + " мс");

        long randomTimeStart = System.currentTimeMillis();
        for (int i = 0; i < 1_000_000; i++) {
            dictionary.getRandom();
        }
        long randomTimeEnd = System.currentTimeMillis();
        System.out.println("Время получения рандома: " + (randomTimeEnd - randomTimeStart) + " мс");

        long deleteTimeStart = System.currentTimeMillis();
        for (int i = 0; i < 1_000_000; i++) {
            dictionary.delete(i);
        }
        long deleteTimeEnd = System.currentTimeMillis();
        System.out.println("Время удаления: " + (deleteTimeEnd - deleteTimeStart) + " мс");
    }
}
