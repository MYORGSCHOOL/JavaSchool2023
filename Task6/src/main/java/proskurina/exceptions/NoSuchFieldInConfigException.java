package proskurina.exceptions;

public class NoSuchFieldInConfigException extends RuntimeException{
    public NoSuchFieldInConfigException(String message) {
        super(message);
    }
}