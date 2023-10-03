package koroliov;

import java.util.ArrayList;
import java.util.Random;

/**
 * Implementation of the implementation method.
 * @author Nikita Koroliov
 * @version 1.0
 */
public class DictionaryImp implements Dictionary<DictionaryImp> {
        private final ArrayList<String> list;

        public DictionaryImp(ArrayList<String> arr) {
            this.list = arr;
        }

        @Override
        public void delete(String s) {
            this.list.remove(s);
            this.list.trimToSize();
        }

        @Override
        public boolean consist(String s) {
            return this.list.contains(s);
        }

        @Override
        public String getRandom() {
            Random random = new Random();
            return this.list.get(random.nextInt(this.list.size()));
        }

        @Override
        public void insert(String s) {
            this.list.add(s);
        }
    }