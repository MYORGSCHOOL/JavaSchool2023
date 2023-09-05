package yanko.testClasses;

import yanko.annotations.TestMethod;

/**
 * класс для тестирования работы аннотаций
 */
public class TestClassNoBeforeNoAfter {
    /**
     * успех выполнения метода
     */
    private boolean isSuccessful;
    /**
     * строка с сообщением
     */
    private String successMessage;

    /**
     * установка успеха true
     */
    @TestMethod(order = 1)
    public void setSuccessfulTrue() {
        isSuccessful = true;
        System.out.println("set successful true");
    }

    /**
     * установка значения строки success
     */
    @TestMethod(order = 2)
    public void setSuccessMessageSuccess() {
        successMessage = "Success";
        System.out.println("set success message success");
    }

    /**
     * установка успеха false
     */
    @TestMethod(order = 3)
    public void setSuccessfulFalse() {
        isSuccessful = false;
        System.out.println("set successful false");
    }

    /**
     * установка значения строки unsuccess
     */
    @TestMethod(order = 4)
    public void setSuccessMessageUnsuccess() {
        successMessage = "Unsuccesss";
        System.out.println("set success message unsucccess");
    }
}
