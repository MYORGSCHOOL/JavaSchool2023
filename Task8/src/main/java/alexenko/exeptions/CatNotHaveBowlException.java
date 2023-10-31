package alexenko.exeptions;

public class CatNotHaveBowlException extends RuntimeException {

    private static final String MESSAGE = "Cat not have bowl, he can't eat.";

    public CatNotHaveBowlException() {
        super(MESSAGE);
    }

}
