package karmanchikova;

public class Task5 {

    public static void main(String[] args) {
        DictionaryImpl<String> dictionary = new DictionaryImpl<>();
        dictionary.insert("axaxa");
        dictionary.insert("ahahha");
        dictionary.insert("6");
        dictionary.insert("csdcsd");
        dictionary.insert("8");
        System.out.println(dictionary);
        dictionary.delete("8");
        System.out.println(dictionary);
    }
}