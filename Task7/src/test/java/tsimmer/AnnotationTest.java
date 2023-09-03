package tsimmer;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tsimmer.annotation.AfterAllMethod;
import tsimmer.annotation.BeforeAllMethod;
import tsimmer.annotation.TestMethod;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AnnotationTest {
    static class Testing {
        @BeforeAllMethod
        public void before() {
            System.out.println("Before");
        }

        @BeforeAllMethod
        public void before1() {
            System.out.println("Before1");
        }
    }

    static class AfterTesting {
        @AfterAllMethod
        public void after() {
            System.out.println("After");
        }

        @AfterAllMethod
        public void after1() {
            System.out.println("After1");
        }
    }

    static class TestOrder {
        @TestMethod(order = 2)
        public static void addTest2() {
            System.out.println("test 2");
        }

        @TestMethod(order = 1)
        public static void addTest1() {
            System.out.println("test 1");
        }

        @TestMethod(order = 3)
        public static void addTest3() {
            System.out.println("test 3");
        }
    }

    @Test
    @DisplayName("Тест неудачного выполнения аннотации @BeforeAllMethod - используются больше одной аннотации")
    void testBeforeAllMethod() {
        assertThrows(RuntimeException.class, () -> TestClass.start(Testing.class));
    }

    @Test
    @DisplayName("Тест неудачного выполнения аннотации @AfterAllMethod - используются больше одной аннотации")
        void testAfterAAllMethod() {
        assertThrows(RuntimeException.class, () -> TestClass.start(AfterTesting.class));
    }
    @Test
    @DisplayName("Тестирование успешного выполнения аннотации @TestMethod")
    void testSuccessOrder() {
        assertDoesNotThrow(()->TestClass.start(TestOrder.class));
    }

}

