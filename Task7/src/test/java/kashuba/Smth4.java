package kashuba;

public class Smth4 {
    @AfterAllMethod
    public int doSmth1() {
        return 0;
    }

    @AfterAllMethod
    public int doSmth2() {
        return 1;
    }

    @TestMethod(order = 1)
    public int doSmth3() {
        return 2;
    }
}