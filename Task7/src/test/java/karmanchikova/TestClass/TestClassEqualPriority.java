package karmanchikova.TestClass;

import karmanchikova.annotations.TestMethod;

public class TestClassEqualPriority {
    /**
     * Успешное выполнение метода
     */
    private boolean isSuccessful;
    /**
     * Сообщение
     */
    private String successMessage;

    /**
     * Установка значения true
     */
    @TestMethod(order = 1)
    public void setSuccessfulTrue() {
        isSuccessful = true;
    }

    /**
     * установка значения строки success
     */
    @TestMethod(order = 2)
    public void setSuccessMessageSuccess() {
        successMessage = "Success";
    }
}