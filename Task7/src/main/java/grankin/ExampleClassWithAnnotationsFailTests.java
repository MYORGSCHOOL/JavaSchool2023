package grankin;

/**
 *   Класс с повторяющимися аннотациями, т.к. в наследуемом классе они уже используются 1 раз
 */
public class ExampleClassWithAnnotationsFailTests extends ExampleClassWithAnnotations{

    /**
     * Метод с повторяющейся аннотацией, которой нельзя повторяться
     */
    @BeforeAllMethod
   public static void befor2() {
       System.out.println("Метод 'до' 2");
    }

    /**
     * Метод с повторяющейся аннотацией, которой нельзя повторяться
     */
   @AfterAllMethod
   public static void after2() {
       System.out.println("Метод 'после' 2");
   }
}
