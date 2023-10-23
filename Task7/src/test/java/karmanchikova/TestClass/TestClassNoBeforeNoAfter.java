package karmanchikova.TestClass;

import karmanchikova.annotations.TestMethod;

public class TestClassNoBeforeNoAfter {
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

    /**
     * Установка значения false
     */
    @TestMethod(order = 3)
    public void setSuccessfulFalse() {
        isSuccessful = false;
    }

    /**
     * Установка значения для unsuccess
     */
    @TestMethod(order = 4)
    public void setSuccessMessageUnsuccess() {
        successMessage = "Unsuccess";
    }
}