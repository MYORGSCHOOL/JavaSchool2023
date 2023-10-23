package dushkina.testClass;

import dushkina.AfterAllMethod;
import dushkina.BeforeAllMethod;
import dushkina.TestMethod;

import java.util.ArrayList;
import java.util.List;

public class AllAnnotations {
    public static List<String> annotationsList = new ArrayList<>();

    @TestMethod(order = 3)
    public void testOrderThree() {
        annotationsList.add("Аннотация test с order = 3");
        System.out.println(annotationsList.get(annotationsList.size() - 1));
    }

    @BeforeAllMethod
    public void before() {
        annotationsList.add("Аннотация before");
        System.out.println(annotationsList.get(annotationsList.size() - 1));
    }

    @AfterAllMethod
    public void after() {
        annotationsList.add("Аннотация after");
        System.out.println(annotationsList.get(annotationsList.size() - 1));
    }

    @TestMethod(order = 2)
    public void testOrderTwo() {
        annotationsList.add("Аннотация test с order = 2");
        System.out.println(annotationsList.get(annotationsList.size() - 1));
    }

    @TestMethod(order = 1)
    public void testOrderOne() {
        annotationsList.add("Аннотация test с order = 1");
        System.out.println(annotationsList.get(annotationsList.size() - 1));
    }
}
