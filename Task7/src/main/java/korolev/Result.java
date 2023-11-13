package korolev;

/**
 * A class for collecting information about method compilation.
 * @author Nikita Koroliov
 * @version 1.0
 */
public class Result {
    private boolean status;
    private String massage;
    private String nameMethod;

    /**
     * constructor for assembling information about the execution of methods in {@link BuildTest}
     * @param s The result of the method execution.
     * @param m Message if there is a method execution error.
     * @param nm The name of the method to be executed.
     */
    public Result(boolean s, String m, String nm) {
        this.status = s;
        this.massage = m;
        this.nameMethod = nm;
    }

    public String getNameMethod() {
        return nameMethod;
    }

    public boolean isStatus() {
        return status;
    }

    public String getMassage() {
        return massage;
    }
}
