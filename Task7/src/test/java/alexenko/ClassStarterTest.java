package alexenko;

import alexenko.test.CatClassTestOnlyWithAnnotationTestMethod;
import alexenko.test.CatTestWithAllAnnotations;
import alexenko.test.CatTestWithAnnotationsBeforeAllMethodsAndTestMethod;
import alexenko.test.CatTestWithAnnotationsTestMethodAndAfterAllMethods;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class ClassStarterTest {

    @Test
    @DisplayName("Проверка класса теста со всеми аннотациями.")
    public void testClassTestWithAllAnnotation() {
        var catTest = (CatTestWithAllAnnotations) TestStarter.start(CatTestWithAllAnnotations.class);
        List<Cat.Action> logActionsCat = catTest.getLogActionsCat();
        List<Cat.Action> expectedListActionsCat = Arrays.asList(
                Cat.Action.WAKE_UP, Cat.Action.EAT, Cat.Action.RUN, Cat.Action.VOICE, Cat.Action.SLEEP
        );
        Assertions.assertEquals(expectedListActionsCat, logActionsCat);
    }

    @Test
    @DisplayName("Проверка класса теста с аннотациями BeforeAllMethods и TestMethod.")
    public void testClassTestWithAnnotationsBeforeAllMethodsAndTestMethod() {
        var catTest = (CatTestWithAnnotationsBeforeAllMethodsAndTestMethod)
                TestStarter.start(CatTestWithAnnotationsBeforeAllMethodsAndTestMethod.class);
        List<Cat.Action> logActionsCat = catTest.getLogActionsCat();
        List<Cat.Action> expectedListActionsCat = Arrays.asList(
                Cat.Action.WAKE_UP, Cat.Action.EAT, Cat.Action.RUN, Cat.Action.VOICE
        );
        Assertions.assertEquals(expectedListActionsCat, logActionsCat);
    }

    @Test
    @DisplayName("Проверка класса теста с аннотациями TestMethod и AfterAllMethods.")
    public void testClassTestWithAnnotationsTestMethodAndAfterAllMethods() {
        var catTest = (CatTestWithAnnotationsTestMethodAndAfterAllMethods)
                TestStarter.start(CatTestWithAnnotationsTestMethodAndAfterAllMethods.class);
        List<Cat.Action> logActionsCat = catTest.getLogActionsCat();
        List<Cat.Action> expectedListActionsCat = Arrays.asList(
                Cat.Action.WAKE_UP, Cat.Action.VOICE, Cat.Action.RUN, Cat.Action.EAT, Cat.Action.SLEEP
        );
        Assertions.assertEquals(expectedListActionsCat, logActionsCat);
    }

    @Test
    @DisplayName("Проверка класса теста только с аннотацией TestMethod.")
    public void testClassTestOnlyWithAnnotationTestMethod() {
        var catTest = (CatClassTestOnlyWithAnnotationTestMethod)
                TestStarter.start(CatClassTestOnlyWithAnnotationTestMethod.class);
        List<Cat.Action> logActionsCat = catTest.getLogActionsCat();
        List<Cat.Action> expectedListActionsCat = Arrays.asList(
                Cat.Action.WAKE_UP, Cat.Action.RUN, Cat.Action.VOICE, Cat.Action.EAT
        );
        Assertions.assertEquals(expectedListActionsCat, logActionsCat);
    }
}
