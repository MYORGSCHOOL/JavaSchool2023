package kashuba;

public class Smth3 {
    @BeforeAllMethod
    public int doSmth1() {
        return 0;
    }

    @BeforeAllMethod
    public int doSmth2() {
        return 1;
    }

    @TestMethod(order = 1)
    public int doSmth3() {
        return 2;
    }
}