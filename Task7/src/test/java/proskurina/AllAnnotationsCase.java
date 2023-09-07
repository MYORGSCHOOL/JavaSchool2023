package proskurina;

import proskurina.annotations.AfterAllMethod;
import proskurina.annotations.BeforeAllMethod;
import proskurina.annotations.Order;
import proskurina.annotations.TestMethod;

import java.util.ArrayList;
import java.util.List;

class AllAnnotationsCase {
    
    // Содержит последовательность вызовов методов класса
    static List<String> callSequence = new ArrayList<>(5); // Количество методов
    
    public
    @BeforeAllMethod
    void beforeAll() {
        callSequence.add("beforeAll");
    }
    
    @Order(2)
    @TestMethod
    void second() {
        callSequence.add("second");
    }
    
    @Order(1)
    @TestMethod
    void first() {
        callSequence.add("first");
    }
    
    // Без явного Order
    @TestMethod
    void third() {
        callSequence.add("third");
    }
    
    @AfterAllMethod
    void afterAll() {
        callSequence.add("afterAll");
    }
    
}