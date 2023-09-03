package grossu.testfortest;

import grossu.testannotation.BeforeAllMethod;


/**
 * Класс для проверки работы ошибочного кода без аннотоции TestMethod
 */
public class ClassWithoutTestMethod {
    /**
     * выполнение метода с аннотацией
     */
    @BeforeAllMethod
    public void beforeAllMethod1() {
        System.out.println("after method1");
    }
}
