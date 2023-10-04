package grankin;

/**
 * Пример класса с верной расстановкой аннотаций
 */
public class ExampleClassWithAnnotations {

    @BeforeAllMethod
    public static void befor() {
        System.out.println("Метод 'до'");
    }

    @AfterAllMethod
    public static void after() {
        System.out.println("Метод 'после'");
    }

    @TestMethod(priority = 2)
    public static void method21() {
        System.out.println("Метод с приоритетом 2");
    }

    @TestMethod(priority = 1)
    public static void method11() {
        System.out.println("Метод с приоритетом 1");
    }

    @TestMethod
    public static void method01() {
        System.out.println("Метод с приоритетом 0");
    }

    @TestMethod
    public static void method02() {
        System.out.println("Метод с приоритетом 0");
    }

    @TestMethod(priority = 1)
    public static void method12() {
        System.out.println("Метод с приоритетом 1");
    }

    @TestMethod(priority = 2)
    public static void method22() {
        System.out.println("Метод с приоритетом 2");
    }

    public static void anotherMethod() {
        System.out.println("Какой-то метод без аннотации");
    }
}
