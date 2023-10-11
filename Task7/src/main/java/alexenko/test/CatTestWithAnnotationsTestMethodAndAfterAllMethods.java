package alexenko.test;

import alexenko.Cat;
import alexenko.annotations.AfterAllMethods;
import alexenko.annotations.BeforeAllMethods;
import alexenko.annotations.TestMethod;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс тест, в котором есть аннотации @TestMethod, @AfterAllMethods.
 */
public class CatTestWithAnnotationsTestMethodAndAfterAllMethods {

    private final static Cat CAT = new Cat();
    private final static List<Cat.Action> LOG_ACTIONS_CAT = new ArrayList<>();

    @TestMethod(1)
    public void testCatToWakeUp() {
        if (Cat.Action.WAKE_UP == CAT.wakeUp()) {
            LOG_ACTIONS_CAT.add(CAT.getAction());
            System.out.println(CAT.getAction().getMessage());
        } else {
            throw new AssertionError();
        }
    }

    @TestMethod(3)
    public void testCatToRun() {
        if (Cat.Action.RUN == CAT.run()) {
            LOG_ACTIONS_CAT.add(CAT.getAction());
            System.out.println(CAT.getAction().getMessage());
        } else {
            throw new AssertionError();
        }
    }

    @TestMethod(2)
    public void testCatToVoice() {
        if (Cat.Action.VOICE == CAT.voice()) {
            LOG_ACTIONS_CAT.add(CAT.getAction());
            System.out.println(CAT.getAction().getMessage());
        } else {
            throw new AssertionError();
        }
    }

    @TestMethod(4)
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
