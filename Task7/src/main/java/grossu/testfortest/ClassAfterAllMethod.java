package grossu.testfortest;

import grossu.testannotation.AfterAllMethod;
import grossu.testannotation.TestMethod;

/**
 * Класс для проверки работы кода в котором есть аннотация AfterAllMethod
 */
public class ClassAfterAllMethod {
    /**
     * выполнение метода с аннотацией
     */
    @TestMethod
    public void test1() {
        System.out.println("Test 1");
    }

    /**
     * выполнение метода с аннотацией
     */
    @AfterAllMethod
    public void afterAllMethod() {
        System.out.println("after method");
    }
}
