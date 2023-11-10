package malakhova;

public class IncorrectNumberBeforeAllMethod {
    @BeforeAllMethod
    public void testAnnotationBefore() {
        System.out.println("test");
    }

    @BeforeAllMethod
    public void testAnnotationBefore2() {
        System.out.println("test2");
    }

    @TestMethod
    public void testAnnotationMethod() {
        System.out.println("testMethod");
    }
}
