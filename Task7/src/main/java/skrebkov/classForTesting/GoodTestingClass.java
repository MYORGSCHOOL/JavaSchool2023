package skrebkov.classForTesting;

import skrebkov.annotation.AfterAllMethod;
import skrebkov.annotation.BeforeAllMethod;
import skrebkov.annotation.TestMethod;

/**
 * Класс для тестирования без ошибок
 */
public class GoodTestingClass {
    public String s = null;

    @BeforeAllMethod
    public void beforeAll() {
        s = "start";
    }

    @TestMethod(priority = 1)
    public void firstMethod() {
        s = "first";
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
