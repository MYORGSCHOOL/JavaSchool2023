package proskurina.exceptions;

public class IllegalTestClassException extends RuntimeException{
    public IllegalTestClassException(String message, Throwable cause){
        super(message, cause);
    }
    
    public IllegalTestClassException(String message){
        super(message);
    }
}