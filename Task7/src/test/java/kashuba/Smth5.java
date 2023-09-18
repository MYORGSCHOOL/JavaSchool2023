package kashuba;

public class Smth5 {
    @TestMethod(order = -1)
    public int doSmth1() {
        return 0;
    }

    @TestMethod(order = 0)
    public int doSmth2() {
        return 1;
    }

    @TestMethod(order = 1)
    public int doSmth3() {
        return 2;
    }
}