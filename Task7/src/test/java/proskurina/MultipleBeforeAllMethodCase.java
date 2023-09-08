package proskurina;

import proskurina.annotations.BeforeAllMethod;
import proskurina.annotations.TestMethod;

class MultipleBeforeAllMethodCase {
    @BeforeAllMethod
    void beforeAllOne() {
    }
    
    @BeforeAllMethod
    void beforeAllTwo() {
    }
    
    @TestMethod
    void test() {
    }
    
}