package koroliov;

import java.util.HashMap;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DictionaryImpTest {
    private DictionaryImp dImp;
    HashMap<Integer, String> maps;

    @BeforeEach
    public void fillData() {
        maps = new HashMap<>();
        maps.put(0, "zero");
        maps.put(1, "one");
        maps.put(2, "two");
        maps.put(3, "three");
        maps.put(4, "four");
        this.dImp = new DictionaryImp(maps);
    }

    @Test
    @DisplayName("A test to check a string in a map.")
    void testConsist() {
        Assertions.assertEquals(true, this.dImp.consist("one"));
    }

    @Test
    @DisplayName("Check: Deletion of a specific row.")
    void testDelete() {
        this.dImp.delete("three");
        Assertions.assertEquals(false, this.dImp.consist("three"));
    }

    @Test
    @DisplayName("A test to get a random element from a map.")
    void testGetRandom() {
        Assertions.assertEquals(true, this.maps.containsValue(this.dImp.getRandom()));
    }

    @Test
    @DisplayName("Check if a new element has been added to the map.")
    void testInsert() {
        this.dImp.insert("five");
        Assertions.assertEquals(true, this.dImp.consist("five"));
    }
}
