package grossu.testfortest;

import grossu.testannotation.AfterAllMethod;
import grossu.testannotation.BeforeAllMethod;
import grossu.testannotation.TestMethod;

/**
 * Класс для проверки работы кода с аннотацией TestMethod с учетом порядка выполения тестов
 */
public class ClassWithTestMethodOrder {
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
    @TestMethod(order=3)
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
}
