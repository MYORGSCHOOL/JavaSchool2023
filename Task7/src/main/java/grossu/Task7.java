package grossu;

import grossu.testexception.UncorrectedUseAnnotation;
import grossu.testfortest.ClassAfterAllMethod;
import grossu.testfortest.ClassWithAllAnnotations;
import grossu.testfortest.ClassWithBeforeAllMethod;
import grossu.testfortest.ClassWithTestMethodNoOrder;
import grossu.testfortest.ClassWithTestMethodOrder;
import grossu.testfortest.ClassWithTestMethodEqualOrder;
import grossu.testfortest.ClassWithoutTestMethod;
import grossu.testfortest.ClassWithTwoAfterAllMethod;
import grossu.testfortest.ClassWithTwoBeforeAllMethod;

/**
 * Класс для взаимодействия с TestRunner
 */
public class Task7 {
    public static void main(String[] args) {
        TestRunner.start(ClassWithAllAnnotations.class.getName());
        TestRunner.start(ClassWithBeforeAllMethod.class.getName());
        TestRunner.start(ClassAfterAllMethod.class.getName());
        TestRunner.start(ClassWithTestMethodNoOrder.class.getName());
        TestRunner.start(ClassWithTestMethodOrder.class.getName());
        TestRunner.start(ClassWithTestMethodEqualOrder.class.getName());
        try {
            TestRunner.start(ClassWithoutTestMethod.class.getName());
        } catch (UncorrectedUseAnnotation e) {
            System.out.println(e.getMessage());
        }
        try {
            TestRunner.start(ClassWithTwoBeforeAllMethod.class.getName());
        } catch (UncorrectedUseAnnotation e) {
            System.out.println(e.getMessage());
        }
        try {
            TestRunner.start(ClassWithTwoAfterAllMethod.class.getName());
        } catch (UncorrectedUseAnnotation e) {
            System.out.println(e.getMessage());
        }

    }
}
