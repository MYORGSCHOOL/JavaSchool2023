package yanko.testClasses;

import yanko.annotations.BeforeAllMethod;

/**
 * класс для тестирования работы аннотаций
 */
public class TestClassTwoBefore {
    /**
     * успех выполнения метода
     */
    private boolean isSuccessful;
    /**
     * строка с сообщением
     */
    private String successMessage;

    /**
     * первоначальная установка значений
     */
    @BeforeAllMethod
    public void setValues() {
        isSuccessful = true;
        successMessage = "Success";
        System.out.println("set values");
    }

    /**
     * сброс значений
     */
    @BeforeAllMethod
    public void resetValues() {
        isSuccessful = false;
        successMessage = null;
        System.out.println("reset values");
    }
}
