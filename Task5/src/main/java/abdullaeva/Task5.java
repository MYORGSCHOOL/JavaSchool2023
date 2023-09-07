package abdullaeva;

public class Task5 {
    public static void main(String[] args) {
        DictionaryImpl<String> dictionary = new DictionaryImpl<>();
        dictionary.insert("abc");
        dictionary.insert("def");
        System.out.println(dictionary.consist("abc"));
        System.out.println(dictionary);
        System.out.println(dictionary.getRandom());
        dictionary.delete("abc");
        System.out.println(dictionary);
        dictionary.delete("def");
        System.out.println(dictionary);
    }
}
