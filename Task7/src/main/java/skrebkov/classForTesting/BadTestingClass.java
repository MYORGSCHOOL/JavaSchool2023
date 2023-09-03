package skrebkov.classForTesting;

import skrebkov.annotation.AfterAllMethod;
import skrebkov.annotation.BeforeAllMethod;
import skrebkov.annotation.TestMethod;

/**
 * Класс для тестирования с ошибкой
 */
public class BadTestingClass extends GoodTestingClass {

    @TestMethod(priority = 1)
    @Override
    public void firstMethod() {
        s = "first";
        throw new RuntimeException("Bad example");
    }

    public String s = null;

    @BeforeAllMethod
    public void beforeAll() {
        s = "start";
    }

    @TestMethod(priority = 2)
    public void secondMethod() {
        s = "second";
    }

    @TestMethod(priority = 3)
    public void thirdMethod() {
        s = "third";
    }

    @AfterAllMethod
    public void afterAll() {
        s = "end";
    }

}
