package yanko;

public class Task5 {
    public static void main(String[] args) {
        DictionaryImpl<Integer> dictionary = new DictionaryImpl<>();
        dictionary.insert(12);
        System.out.println(dictionary.consist(12));
        System.out.println(dictionary);

    }
}
