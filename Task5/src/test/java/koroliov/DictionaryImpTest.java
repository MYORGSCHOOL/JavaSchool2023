package koroliov;

import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DictionaryImpTest {
    private ArrayList<String> list;
    private DictionaryImp dImp;

    @BeforeEach
    public void addData() {
        this.list = new ArrayList<>();
        this.list.add("one");
        this.list.add("two");
        this.list.add("three");

        this.dImp = new DictionaryImp(list);
    }

    @Test
    void testConsist() {
        Assertions.assertEquals(true, this.dImp.consist("one"));
    }

    @Test
    void testDelete() {
        this.dImp.delete("two");
        Assertions.assertEquals(false, this.dImp.consist("two"));
    }

    @Test
    void testGetRandom() {
        Assertions.assertEquals(true, 
            this.dImp.consist(this.dImp.getRandom()));
    }

    @Test
    void testInsert() {
        this.dImp.insert("four");
        Assertions.assertEquals(true, this.dImp.consist("four"));
    }
}
