package proskurina;

import proskurina.annotations.AfterAllMethod;
import proskurina.annotations.TestMethod;

class MultipleAfterAllMethodCase {
    @AfterAllMethod
    void afterAllOne() {
    }
    
    @AfterAllMethod
    void afterAllTwo() {
    }
    
    @TestMethod
    void test() {
    }
    
}