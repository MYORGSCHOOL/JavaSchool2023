package karmanchikova.TestClass;

import karmanchikova.annotations.AfterAllMethod;
import karmanchikova.annotations.BeforeAllMethod;
import karmanchikova.annotations.TestMethod;

public class TestClass {
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
     * Установка значения true
     */
    @TestMethod(order = 1)
    public void setSuccessfulTrue() {
        isSuccessful = true;
    }

    /**
     * Установка значения для success
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

    /**
     * Сброс значений
     */
    @AfterAllMethod
    public void resetValuesAfterAllMethod() {
        isSuccessful = false;
        successMessage = null;
    }
}