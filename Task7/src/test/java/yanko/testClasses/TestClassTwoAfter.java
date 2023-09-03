package yanko.testClasses;

import yanko.annotations.AfterAllMethod;

/**
 * класс для тестирования работы аннотаций
 */
public class TestClassTwoAfter {
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
    @AfterAllMethod
    public void setValues() {
        isSuccessful = true;
        successMessage = "Success";
        System.out.println("set values");
    }

    /**
     * сброс значений
     */
    @AfterAllMethod
    public void resetValues() {
        isSuccessful = false;
        successMessage = null;
        System.out.println("reset values");
    }
}
