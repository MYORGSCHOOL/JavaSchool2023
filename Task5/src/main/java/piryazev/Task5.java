package piryazev;


public class Task5 {
    public static void main(String[] args) {
        DictionaryImpl<String> dictionary = new DictionaryImpl<>();
        dictionary.insert("1");
        dictionary.delete("1");
        dictionary.insert("1");
        System.out.println(dictionary);
    }
}
