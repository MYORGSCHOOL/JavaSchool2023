package babadzhanov;

import babadzhanov.annotations.AfterAllMethod;
import babadzhanov.annotations.BeforeAllMethod;
import babadzhanov.annotations.DisplayName;
import babadzhanov.annotations.TestMethod;

import java.lang.reflect.InvocationTargetException;

public class Task7 {

    public static void main(String[] args)
            throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        new TestFramework().start(TestClassExample.class);
    }

    static class TestClassExample {

        @BeforeAllMethod
        public void setUp() {
            System.out.println("Запустился метод с аннотацией Before");
        }

        @AfterAllMethod
        public void tearDown() {
            System.out.println("Запустился метод с аннотацией After");
        }

        @TestMethod(order = 1)
        @DisplayName("Тест с порядковым номером 1")
        public void test1() {
            System.out.println("Выполняем тестовый метод c порядком 1");
        }

        @TestMethod(order = 2)
        @DisplayName("Тест с порядковым номером 2")
        public void test2() {
            System.out.println("Выполняем тестовый метод c порядком 2");
        }

        @TestMethod(order = 3)
        @DisplayName("Тест с порядковым номером 3")
        public void test3() {
            System.out.println("Выполняем тестовый метод c порядком 3");
        }

        @TestMethod(order = 4)
        @DisplayName("Тест с порядковым номером 4")
        public void test4() {
            throw new RuntimeException("Тест провалился");
        }
    }
}
