package babadzhanov;

public class Task5 {
    public static void main(String[] args) {
        DictionaryImpl<String> dictionary = new DictionaryImpl<>();
        dictionary.insert("String1");
        dictionary.insert("String2");
        dictionary.insert("String3");
        System.out.println(dictionary);
        dictionary.delete("String2");
        System.out.println(dictionary);
        System.out.println(dictionary.contains("String2"));
        System.out.println(dictionary.getRandom());
    }
}
