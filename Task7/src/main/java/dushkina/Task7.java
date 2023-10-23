package dushkina;

import dushkina.exception.TestAnnotationException;
import dushkina.testClass.*;

import java.lang.reflect.InvocationTargetException;

public class Task7 {
    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        try {
            System.out.println("Тест: класс == null");
            TestRunner.start(null);
        } catch (TestAnnotationException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Тест: все аннотации");
        TestRunner.start(AllAnnotations.class);
        System.out.println("Тест: order аннотации");
        TestRunner.start(OrderTestAnnotations.class);
        try {
            System.out.println("Тест: no аннотации");
            TestRunner.start(NoAnnotations.class);
        } catch (TestAnnotationException e) {
            System.out.println(e.getMessage());
        }
        try {
            System.out.println("Тест: two before аннотации");
            TestRunner.start(TwoBeforeAnnotations.class);
        } catch (TestAnnotationException e) {
            System.out.println(e.getMessage());
        }
        try {
            System.out.println("Тест: two after аннотации");
            TestRunner.start(TwoAfterAnnotations.class);
        } catch (TestAnnotationException e) {
            System.out.println(e.getMessage());
        }
    }
}
