package alexenko.test;

import alexenko.Cat;
import alexenko.annotations.BeforeAllMethods;
import alexenko.annotations.TestMethod;
import alexenko.annotations.AfterAllMethods;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс тест, в котором все аннотации, то есть @BeforeAllMethods, @TestMethod, @AfterAllMethods.
 */
public class CatTestWithAllAnnotations {

    private final static Cat CAT = new Cat();
    private final static List<Cat.Action> LOG_ACTIONS_CAT = new ArrayList<>();

    @BeforeAllMethods
    public void testCatToWakeUp() {
        if (Cat.Action.WAKE_UP == CAT.wakeUp()) {
            LOG_ACTIONS_CAT.add(CAT.getAction());
            System.out.println(CAT.getAction().getMessage());
        } else {
            throw new AssertionError();
        }
    }

    @TestMethod(2)
    public void testCatToRun() {
        if (Cat.Action.RUN == CAT.run()) {
            LOG_ACTIONS_CAT.add(CAT.getAction());
            System.out.println(CAT.getAction().getMessage());
        } else {
            throw new AssertionError();
        }
    }

    @TestMethod(3)
    public void testCatToVoice() {
        if (Cat.Action.VOICE == CAT.voice()) {
            LOG_ACTIONS_CAT.add(CAT.getAction());
            System.out.println(CAT.getAction().getMessage());
        } else {
            throw new AssertionError();
        }
    }

    @TestMethod(1)
    public void testCatToEat() {
        if (Cat.Action.EAT == CAT.eat()) {
            LOG_ACTIONS_CAT.add(CAT.getAction());
            System.out.println(CAT.getAction().getMessage());
        } else {
            throw new AssertionError();
        }
    }

    @AfterAllMethods
    public void testCatToSleep() {
        if (Cat.Action.SLEEP == CAT.sleep()) {
            LOG_ACTIONS_CAT.add(CAT.getAction());
            System.out.println(CAT.getAction().getMessage());
        } else {
            throw new AssertionError();
        }
    }

    public List<Cat.Action> getLogActionsCat() {
        return LOG_ACTIONS_CAT;
    }
}
