package kashuba;

public class Smth {
    @BeforeAllMethod
    public int doSmth() {
        return 0;
    }

    @TestMethod(order = 1)
    public int doSmth1() {
        return 1;
    }

    @TestMethod(order = 3)
    public int doSmth2() {
        return 2;
    }

    @TestMethod(order = 2)
    public int doSmth3() {
        return 3;
    }

    @AfterAllMethod
    public int doSmth4() {
        return 4;
    }
}