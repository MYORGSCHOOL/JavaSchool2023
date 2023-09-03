package yanko.testClasses;

import yanko.annotations.TestMethod;

/**
 * класс для тестирования работы аннотаций
 */
public class TestClassEqualPriority {
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
    @TestMethod(order = 1)
    public void setSuccessMessageSuccess() {
        successMessage = "Success";
        System.out.println("set success message success");
    }
}
