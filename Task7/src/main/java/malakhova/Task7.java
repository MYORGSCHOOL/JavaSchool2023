package malakhova;

public class Task7 {
    @BeforeAllMethod()
    public static void printAnnotation() {
        System.out.println("Метод Before");
    }

    @TestMethod(order = 2)
    public static void printTestAnnotation() {
        System.out.println("Метод Test2");
    }

    @TestMethod(order = 1)
    public static void printTestAnnotation1() {
        System.out.println("Метод Test1");
    }

    @TestMethod(order = 4)
    public static void printTestAnnotation4() {
        System.out.println("Метод Test4");
    }

    @TestMethod(order = 3)
    public static void printTestAnnotation2() {
        System.out.println("Метод Test3");
    }

    @AfterAllMethod()
    public static void printAnnotationAfter() {
        System.out.println("Метод After");
    }

    public static void main(String[] args) {
        System.out.println("Порядок выполнения методов:");
        TestClass.start(Task7.class);
    }
}
