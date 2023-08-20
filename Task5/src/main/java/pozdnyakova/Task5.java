package pozdnyakova;


public class Task5 {
    public static void main(String[] args) {
        DictionaryImplements<Character> dictionary = new DictionaryImplements<>();
        dictionary.insert('a');
        dictionary.insert('b');
        dictionary.insert('c');
        dictionary.insert('d');
        dictionary.insert('e');
        dictionary.insert('f');
        System.out.println(dictionary.consist('c'));
        System.out.println(dictionary.consist('r'));
        dictionary.delete('f');
        dictionary.delete('b');
        System.out.println(dictionary.consist('f'));
        System.out.println(dictionary.getRandom());
        System.out.println(dictionary.getRandom());
        try {
            dictionary.insert('a');
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        try {
            dictionary.delete('f');
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

    }

}
