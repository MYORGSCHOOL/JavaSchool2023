package grossu.testfortest;

import grossu.testannotation.AfterAllMethod;
import grossu.testannotation.BeforeAllMethod;
import grossu.testannotation.TestMethod;

/**
 * Класс для проверки кода в котором есть аннотация BeforeAllMethod
 */
public class ClassWithBeforeAllMethod {
    /**
     * выполнение метода с аннотацией
     */
    @BeforeAllMethod
    public void beforeAllMethod() {
        System.out.println("method before");
    }

    /**
     * выполнение метода с аннотацией
     */
    @TestMethod
    public void test1() {
        System.out.println("Test 1");
    }


}
