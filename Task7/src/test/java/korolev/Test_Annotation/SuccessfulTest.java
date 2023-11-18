package korolev.Test_Annotation;


import korolev.Annotation.TestMethod;

public class SuccessfulTest {
    @TestMethod(order = 2)
    public void testik() {
        System.out.println("Go Test!");
    }
}
