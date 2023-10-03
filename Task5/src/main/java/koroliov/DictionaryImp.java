package koroliov;

import java.util.Random;
import java.util.HashMap;

/**
 * Implementation of the implementation method.
 * @author Nikita Koroliov
 * @version 1.0
 */
public class DictionaryImp implements Dictionary<DictionaryImp> {
        private final HashMap<Integer, String> maps;

        public DictionaryImp(HashMap<Integer, String> maps) {
            this.maps = maps;
        }

        @Override
        public void delete(String s) {
            for (int i = 0; i < this.maps.size(); i++) {
                if (this.maps.get(i) == s) {
                    this.maps.remove(i, s);
                }
            }
        }

        @Override
        public boolean consist(String s) {
            return this.maps.containsValue(s);
        }

        @Override
        public String getRandom() {
            Random random = new Random();
            return this.maps.get(random.nextInt(this.maps.size()));
        }

        @Override
        public void insert(String s) {
            this.maps.put(this.maps.size(), s);
        }
    }