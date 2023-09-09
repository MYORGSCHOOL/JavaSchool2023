package dushkina;

import java.util.ArrayList;
import java.util.HashMap;

public class Task5 {
    public static void main(String[] args) {
        DictionaryImpl<String> dictionaryImpl = new DictionaryImpl<>();
        dictionaryImpl.insert("1");
        dictionaryImpl.insert("2");
        dictionaryImpl.insert("3");
        dictionaryImpl.insert("4");
        System.out.println(dictionaryImpl);

        dictionaryImpl.delete("1");
        System.out.println(dictionaryImpl);

        System.out.println(dictionaryImpl.consist("1"));
        System.out.println(dictionaryImpl.consist("2"));

        System.out.println(dictionaryImpl.getRandom());
    }
}
