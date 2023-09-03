package grossu.testfortest;

import grossu.testannotation.AfterAllMethod;
import grossu.testannotation.BeforeAllMethod;
import grossu.testannotation.TestMethod;

/**
 * класс для проверки работы кода в котором есть все аннотации
 */
public class ClassWithAllAnnotations {
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
    @TestMethod(order = 1)
    public void test1() {
        System.out.println("Test 1");
    }

    /**
     * выполнение метода с аннотацией
     */
    @TestMethod
    public void test3() {
        System.out.println("Test 3");
    }

    /**
     * выполнение метода с аннотацией
     */
    @TestMethod(order = 2)
    public void test2() {
        System.out.println("Test 2");
    }

    /**
     * выполнение метода с аннотацией
     */
    @AfterAllMethod
    public void afterAllMethod() {
        System.out.println("after method");
    }
}
