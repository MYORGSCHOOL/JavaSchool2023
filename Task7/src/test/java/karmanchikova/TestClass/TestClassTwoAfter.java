package karmanchikova.TestClass;

import karmanchikova.annotations.AfterAllMethod;

public class TestClassTwoAfter {
    /**
     * Успешное выполнение метода
     */
    private boolean isSuccessful;
    /**
     * Сообщение
     */
    private String successMessage;
    /**
     * Изначальные значения
     */
    @AfterAllMethod
    public void setValues() {
        isSuccessful = true;
        successMessage = "Success";
    }
    /**
     * Сброс значений
     */
    @AfterAllMethod
    public void resetValues() {
        isSuccessful = false;
        successMessage = null;
    }
}
