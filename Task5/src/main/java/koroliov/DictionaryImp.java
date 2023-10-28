package koroliov;

import java.util.Random;
import javax.management.openmbean.KeyAlreadyExistsException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;

/**
 * Implementation of the implementation method.
 * @author Nikita Koroliov
 * @version 1.0
 */
public class DictionaryImp<G> implements Dictionary<G> {
        private final HashMap<G, Integer> maps;
        private final ArrayList<G> list;

        public DictionaryImp() {
            this.maps = new HashMap<>();
            this.list = new ArrayList<>();
        }

        @Override
        public void delete(G s) {
            if (this.maps.isEmpty() && this.list.isEmpty()) {
                throw new NoSuchElementException("The dictionary is empty.");
            }
            if (!this.consist(s)) {
                throw new NoSuchElementException("The item is missing from the dictionary.");
            }

            final int index = this.maps.remove(s);
            if (index < this.list.size() - 1 && index >= 0) {
                voidRemoval(index);
                this.maps.replace(this.list.get(this.list.size() - 1), index);
            }

            this.list.remove(this.list.size() - 1);
            this.list.trimToSize();
        }

        @Override
        public boolean consist(G s) {
            return this.maps.containsKey(s);
        }

        @Override
        public G getRandom() {
            if (this.list.isEmpty()) {
                throw new NoSuchElementException("The dictionary is empty.");
            }

            Random random = new Random();
            return this.list.get((int) random.nextInt(this.list.size()));
        }

        @Override
        public void insert(G s) {
            if (consist(s)) {
                throw new KeyAlreadyExistsException("Such an element already exists.");
            }

            this.maps.put(s, this.list.size() - 1);
            this.list.add(s);
        }

        /**
         * The method swaps the last item in the list with the item to be deleted.
         * @param index The number of the item in the list.
         */
        private void voidRemoval(int index) {
            final G temp = this.list.get(this.list.size() - 1);
            this.list.set(this.list.size() - 1, this.list.get(index));
            this.list.set(index, temp);
        }
    }