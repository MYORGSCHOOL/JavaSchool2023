package tsimmer;

public class Task7 {
    public static void main(String[] args) {
        Class<Methods> test = Methods.class;
        try {
            TestClass.start(test);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
