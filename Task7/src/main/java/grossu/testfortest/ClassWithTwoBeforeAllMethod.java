package grossu.testfortest;

import grossu.testannotation.AfterAllMethod;
import grossu.testannotation.BeforeAllMethod;
import grossu.testannotation.TestMethod;

/**
 * Класс проверящий работу ошибочного кода содержащего две аннотации BeforeAllMethod
 */
public class ClassWithTwoBeforeAllMethod {
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
    @BeforeAllMethod
    public void beforeAllMethod() {
        System.out.println("after method");
    }
    /**
     * выполнение метода с аннотацией
     */
    @BeforeAllMethod
    public void beforeAllMethod1() {
        System.out.println("after method1");
    }
}
