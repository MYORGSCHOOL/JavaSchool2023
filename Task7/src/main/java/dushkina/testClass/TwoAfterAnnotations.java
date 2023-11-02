package dushkina.testClass;

import dushkina.AfterAllMethod;

import java.util.ArrayList;
import java.util.List;

public class TwoAfterAnnotations {
    public static List<String> annotationsList = new ArrayList<>();

    @AfterAllMethod
    public void after1() {
        annotationsList.add("Аннотация after");
        System.out.println(annotationsList.get(annotationsList.size() - 1));
    }

    @AfterAllMethod
    public void after2() {
        annotationsList.add("Аннотация after");
        System.out.println(annotationsList.get(annotationsList.size() - 1));
    }
}
