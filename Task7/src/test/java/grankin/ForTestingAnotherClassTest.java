package grankin;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ForTestingAnotherClassTest {

    @Test
    @DisplayName("Успешный запуск всех нужных методов")
    public void testSuccessesStartMethods() {
        Assertions.assertEquals(8, ForTestingAnotherClass.start("grankin.ExampleClassWithAnnotations"));
    }

    @Test
    @DisplayName("Неуспешный запуск всех нужных методов")
    public void testUnSuccessesStartMethods() {
        String message = "grankin.IllegalCountBeforeOrAfterAnnotationsException";
        try {
            ForTestingAnotherClass.start("grankin.ExampleClassWithAnnotationsFailTests");
        }
        catch (RuntimeException e) {
            Assertions.assertEquals(message, e.getMessage());
        }
    }
}
