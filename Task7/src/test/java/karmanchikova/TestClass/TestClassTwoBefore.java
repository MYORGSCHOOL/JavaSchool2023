package karmanchikova.TestClass;

import karmanchikova.annotations.BeforeAllMethod;

public class TestClassTwoBefore {
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
    @BeforeAllMethod
    public void setValues() {
        isSuccessful = true;
        successMessage = "Success";
    }
    /**
     * Сброс значений
     */
    @BeforeAllMethod
    public void resetValues() {
        isSuccessful = false;
        successMessage = null;
    }
}
