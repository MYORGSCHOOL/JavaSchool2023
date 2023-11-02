package dushkina.testClass;

import dushkina.TestMethod;

import java.util.ArrayList;
import java.util.List;

public class OrderTestAnnotations {
    public static List<String> annotationsList = new ArrayList<>();

    @TestMethod
    public void testOrder() {
        annotationsList.add("Аннотация test с order = 0");
        System.out.println(annotationsList.get(annotationsList.size() - 1));
    }

    @TestMethod(order = 10)
    public void testOrder10() {
        annotationsList.add("Аннотация test с order = 10");
        System.out.println(annotationsList.get(annotationsList.size() - 1));
    }

    @TestMethod(order = 20)
    public void testOrder20() {
        annotationsList.add("Аннотация test с order = 20");
        System.out.println(annotationsList.get(annotationsList.size() - 1));
    }

    @TestMethod(order = 5)
    public void testOrder5() {
        annotationsList.add("Аннотация test с order = 5");
        System.out.println(annotationsList.get(annotationsList.size() - 1));
    }
}
