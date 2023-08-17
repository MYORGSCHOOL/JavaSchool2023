package proskurina;


public class Task5 {
    
    public static void main(String[] args) {
        DictionaryImpl<Integer> dictionary = new DictionaryImpl<>();
        
        dictionary.insert(1);
        dictionary.consist(1);
        dictionary.getRandom();
        dictionary.delete(1);
    }
}