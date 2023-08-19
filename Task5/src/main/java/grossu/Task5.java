package grossu;

/**
 * Класс для взаимодействия с DictionaryImpl
 */
public class Task5 {
    public static void main(String[] args) {
        DictionaryImpl<String> dictionary = new DictionaryImpl<>();
        dictionary.insert("5");
        dictionary.insert("6");
        dictionary.insert("8");
        dictionary.insert("hohohho");
        System.out.println(dictionary);
        dictionary.delete("6");
        System.out.println(dictionary);
        System.out.println(dictionary.getRandom());
    }
}
