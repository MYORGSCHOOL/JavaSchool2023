package malakhova;

public class Task5 {
    public static void main(String[] args) {
        DictionaryImpl<String> dictionary = new DictionaryImpl<>();
        dictionary.insert("one");
        dictionary.insert("two");
        dictionary.insert("three");
        dictionary.insert("four");
        dictionary.insert("five");
        System.out.println(dictionary);
        try {
            dictionary.delete("four");
        } catch (IllegalArgumentException e) {
            System.out.println("В словаре нет элемента, который вы хотите удалить");
        }
        System.out.println("Словарь после удаление элемента: " + dictionary);
        System.out.println("Проверка наличия элемента: " + dictionary.consist("one"));
        try {
            System.out.println("Случайный элемент словаря: " + dictionary.getRandom());
        } catch (IllegalArgumentException e) {
            System.out.println("Словарь пустой");
        }
    }
}
