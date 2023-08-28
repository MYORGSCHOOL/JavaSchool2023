package druzhinin;

/**
 * Класс для использования DictionaryImplementation.
 */
public class Task5 {
    public static void main(String[] args) {
        Dictionary<String> dictionary = new DictionaryImplementation<>();
        dictionary.insert("A");
        dictionary.insert("B");
        dictionary.insert("C");
        dictionary.insert("D");
        dictionary.insert("E");
        dictionary.insert("F");
        dictionary.insert("F");
        System.out.println("Словарь после вставки элементов:\n" + dictionary);
        dictionary.delete("B");
        dictionary.delete("C");
        dictionary.delete("E");
        System.out.println("Словарь после удаления элементов:\n" + dictionary);
        System.out.println("Случайный элемент словаря: " + dictionary.getRandom());
        System.out.println("Содержит ли словарь элемент B? " + dictionary.contains("B"));
        System.out.println("Содержит ли словарь элемент A? " + dictionary.contains("A"));
    }
}
