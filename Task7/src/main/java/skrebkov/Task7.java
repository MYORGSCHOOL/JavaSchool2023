package skrebkov;

import skrebkov.classForTesting.BadTestingClass;
import skrebkov.classForTesting.GoodTestingClass;

import java.lang.reflect.InvocationTargetException;

public class Task7 {
    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        var goodTestingClass = new GoodTestingClass();
        Testing.runTest(goodTestingClass);

        var badTestingClass = new BadTestingClass();
        Testing.runTest(badTestingClass);
    }
}
