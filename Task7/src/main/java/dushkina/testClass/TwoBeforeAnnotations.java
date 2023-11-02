package dushkina.testClass;

import dushkina.BeforeAllMethod;

import java.util.ArrayList;
import java.util.List;

public class TwoBeforeAnnotations {
    public static List<String> annotationsList = new ArrayList<>();

    @BeforeAllMethod
    public void after1() {
        annotationsList.add("Аннотация after");
        System.out.println(annotationsList.get(annotationsList.size() - 1));
    }

    @BeforeAllMethod
    public void after2() {
        annotationsList.add("Аннотация after");
        System.out.println(annotationsList.get(annotationsList.size() - 1));
    }
}
