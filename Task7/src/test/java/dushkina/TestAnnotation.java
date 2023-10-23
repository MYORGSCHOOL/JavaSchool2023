package dushkina;

import dushkina.exception.TestAnnotationException;
import dushkina.testClass.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class TestAnnotation {
    @Test
    @DisplayName("Проверка порядка вызова при выставленных аннотациях")
    public void testNullClass() throws Exception {
        Assertions.assertThrows(TestAnnotationException.class, () -> TestRunner.start(null));
    }

    @Test
    @DisplayName("Проверка порядка вызова при выставленных аннотациях")
    public void testAllAnnotation() throws Exception {
        List<String> test = Arrays.asList("Аннотация before", "Аннотация test с order = 1", "Аннотация test с order = 2",
                "Аннотация test с order = 3", "Аннотация after");
        TestRunner.start(AllAnnotations.class);
        Assertions.assertEquals(test, AllAnnotations.annotationsList);
    }

    @Test
    @DisplayName("Проверка поведения в случае отсутствия аннотаций")
    public void testNoAnnotation() throws Exception {
        Assertions.assertThrows(TestAnnotationException.class, () -> TestRunner.start(NoAnnotations.class));
    }

    @Test
    @DisplayName("Проверка порядка вызова при выставленных порядках")
    public void testOrderTestAnnotation() throws Exception {
        List<String> test = Arrays.asList("Аннотация test с order = 0", "Аннотация test с order = 5", "Аннотация test с order = 10", "Аннотация test с order = 20");
        TestRunner.start(OrderTestAnnotations.class);
        Assertions.assertEquals(test, OrderTestAnnotations.annotationsList);
    }

    @Test
    @DisplayName("Проверка поведения в случае AfterAllMethod>1")
    public void testTwoAfterAnnotation() throws Exception {
        Assertions.assertThrows(TestAnnotationException.class, () -> TestRunner.start(TwoAfterAnnotations.class));
    }

    @Test
    @DisplayName("Проверка поведения в случае BeforeAllMethod>1")
    public void testTwoBeforeAnnotation() throws Exception {
        Assertions.assertThrows(TestAnnotationException.class, () -> TestRunner.start(TwoBeforeAnnotations.class));
    }
}
