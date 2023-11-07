package malakhova;

public class IncorrectNumberAfterAllMethod {
    @AfterAllMethod
    public void testAnnotationAfter() {
        System.out.println("test");
    }

    @AfterAllMethod
    public void testAnnotationAfter2() {
        System.out.println("test2");
    }

    @TestMethod
    public void testAnnotationMethod() {
        System.out.println("testMethod");
    }
}
