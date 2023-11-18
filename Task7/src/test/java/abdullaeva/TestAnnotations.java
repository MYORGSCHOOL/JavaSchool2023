package abdullaeva;

import abdullaeva.classesForTest.ClassWithAfterAllAnnotation;
import abdullaeva.classesForTest.ClassWithAllAnnotation;
import abdullaeva.classesForTest.ClassWithBeforeAllAnnotation;
import abdullaeva.classesForTest.ClassWithTestOrders;
import abdullaeva.classesForTest.ClassWithTwoAfterAllAnnotation;
import abdullaeva.classesForTest.ClassWithTwoBeforeAllAnnotation;
import abdullaeva.classesForTest.ClassWithTwoSameTestOrders;
import abdullaeva.classesForTest.ClassWithoutTestOrders;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TestAnnotations {
    @Test
    @DisplayName("Проверка корректного вызова тест-методов класса с использованием всех кастомных аннотаций")
    public void testCorrectClassWithAllAnnotationMethods() {
        Class<ClassWithAllAnnotation> test = ClassWithAllAnnotation.class;
        Assertions.assertDoesNotThrow(() -> TestRunner.start(test));
        Assertions.assertEquals("BeforeAllMethod; TestMethod(order = 1); TestMethod(order = 2); AfterAllMethod; ",
                ClassWithAllAnnotation.getResultString());
    }

    @Test
    @DisplayName("Проверка вызова тест-метода класса, в котором нет методов с аннотацией TestMethod")
    public void testFailedClassWithoutTestOrdersMethods() {
        Class<ClassWithoutTestOrders> test = ClassWithoutTestOrders.class;
        Assertions.assertThrows(NoSuchMethodException.class, () -> TestRunner.start(test));
    }

    @Test
    @DisplayName("Проверка корректного вызова тест-методов класса с аннотацией TestMethod по заданным приоритетам")
    public void testCorrectClassWithTestOrdersMethods() {
        Class<ClassWithTestOrders> test = ClassWithTestOrders.class;
        Assertions.assertDoesNotThrow(() -> TestRunner.start(test));
        Assertions.assertEquals("TestMethod(order = 1); TestMethod(order = 2); TestMethod(order = 3); ",
                ClassWithTestOrders.getResultString());
    }

    @Test
    @DisplayName("Проверка корректного вызова тест-методов класса с аннотацией TestMethod c одинаковыми приоритетами")
    public void testCorrectClassWithTwoSameTestOrdersMethods() {
        Class<ClassWithTwoSameTestOrders> test = ClassWithTwoSameTestOrders.class;
        Assertions.assertDoesNotThrow(() -> TestRunner.start(test));
        Assertions.assertEquals("TestMethod(order = 1); TestMethod(order = 2); TestMethod(order = 2) same; ",
                ClassWithTwoSameTestOrders.getResultString());
    }

    @Test
    @DisplayName("Проверка корректного вызова тест-методов класса с аннотацией BeforeAllMethod")
    public void testCorrectClassWithBeforeAllAnnotationMethod() {
        Class<ClassWithBeforeAllAnnotation> test = ClassWithBeforeAllAnnotation.class;
        Assertions.assertDoesNotThrow(() -> TestRunner.start(test));
        Assertions.assertEquals("BeforeAllMethod; TestMethod(order = 1); TestMethod(order = 2); ",
                ClassWithBeforeAllAnnotation.getResultString());
    }

    @Test
    @DisplayName("Проверка корректного вызова тест-методов класса с аннотацией AfterAllMethod")
    public void testCorrectClassWithAfterAllAnnotationMethod() {
        Class<ClassWithAfterAllAnnotation> test = ClassWithAfterAllAnnotation.class;
        Assertions.assertDoesNotThrow(() -> TestRunner.start(test));
        Assertions.assertEquals("TestMethod(order = 1); TestMethod(order = 2); AfterAllMethod; ",
                ClassWithAfterAllAnnotation.getResultString());
    }

    @Test
    @DisplayName("Проверка вызова тест-методов класса, в котором два метода с аннотацией BeforeAllMethod")
    public void testFailedClassWithTwoBeforeAllAnnotationMethods() {
        Class<ClassWithTwoBeforeAllAnnotation> test = ClassWithTwoBeforeAllAnnotation.class;
        Assertions.assertThrows(AnnotationUniquenessException.class, () -> TestRunner.start(test));
    }

    @Test
    @DisplayName("Проверка вызова тест-методов класса, в котором два метода с аннотацией AfterAllMethod")
    public void testFailedClassWithTwoAfterAllAnnotationMethods() {
        Class<ClassWithTwoAfterAllAnnotation> test = ClassWithTwoAfterAllAnnotation.class;
        Assertions.assertThrows(AnnotationUniquenessException.class, () -> TestRunner.start(test));
    }
}
