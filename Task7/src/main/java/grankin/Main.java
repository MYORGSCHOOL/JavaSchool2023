package grankin;

public class Main {
    public static void main(String[] args) {
        System.out.println("\nТестируем класс ExampleClassWithAnnotations");
        try {
            ForTestingAnotherClass.start("grankin.ExampleClassWithAnnotations");
        }
        catch (RuntimeException e) {
            System.err.println(e.getMessage());
        }

        System.out.println("\nТестируем класс ExampleClassWithAnnotationsFailTests");
        try {
            ForTestingAnotherClass.start("grankin.ExampleClassWithAnnotationsFailTests");
        }
        catch (RuntimeException e) {
            System.err.println(e.getMessage());
        }
    }
}