package grossu.testfortest;

import grossu.testannotation.AfterAllMethod;
import grossu.testannotation.TestMethod;

/**
 * Класс проверяющий работу ошибочного кода сс двумя аннотациями AfterAllMethod
 */
public class ClassWithTwoAfterAllMethod {
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
    /**
     * выполнение метода с аннотацией
     */
    @AfterAllMethod
    public void afterAllMethod1() {
        System.out.println("after method1");
    }
}
