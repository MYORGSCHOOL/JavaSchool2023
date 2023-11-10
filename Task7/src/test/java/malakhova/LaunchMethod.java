package malakhova;

public class LaunchMethod {
    private static String countTest;

    @BeforeAllMethod()
    public static void printAnnotation() {
        countTest = "1";
    }

    @AfterAllMethod()
    public static void printAnnotationAfter() {
        countTest += "3";
    }

    @TestMethod()
    public static void printTestAnnotation2() {
        countTest += "2";
    }

    public static boolean launchMethod() {
        TestClass.start(LaunchMethod.class);
        if (countTest.equals("123")) {
            return true;
        }
        return false;
    }
}
