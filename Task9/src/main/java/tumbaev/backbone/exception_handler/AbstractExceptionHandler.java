package tumbaev.backbone.exception_handler;

import tumbaev.backbone.ResponseEntity;
import tumbaev.backbone.exception.UnsupportedExceptionTypeException;

import java.lang.reflect.ParameterizedType;
import java.util.logging.Logger;

/**
 * Abstract class for exception handlers. One handler can only handle one type of exception.
 * <br/>
 * Occurred exception should be passed in {@link #handle} method.
 *
 * @param <T> exception type which this handler processes
 * @see ExceptionHandlerManager
 */
public class AbstractExceptionHandler<T extends Throwable> {
    protected static final Logger logger = Logger.getLogger(AbstractExceptionHandler.class.getName());

    /**
     * Class of supported exception. Will be equal to class of {@link T}.
     */
    private final Class<T> supportedExceptionClass;

    protected AbstractExceptionHandler() {
        ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();
        this.supportedExceptionClass = (Class<T>) type.getActualTypeArguments()[0];
    }

    /**
     * Handles exception of type {@link T}.
     * <br/>
     * If exception is not supported ({@link #supports} returned false), exception is thrown.
     *
     * @param e exception to handle, must be of type {@link T}
     * @return response entity with information, that should be sent in response
     * @throws UnsupportedExceptionTypeException if provided exception is not supported ({@link #supports} returned false)
     */
    public ResponseEntity handle(Throwable e) throws UnsupportedExceptionTypeException {
        return null;
    }

    /**
     * Checks if this handler supports exceptions of type exceptionClass.
     * <br/>
     * If this method returned true, exception can be processed by {@link #handle} method.
     *
     * @param exceptionClass class to check
     * @return true if handler supports this exception, false otherwise
     */
    public boolean supports(Class<? extends Throwable> exceptionClass) {
        return exceptionClass.equals(supportedExceptionClass);
    }

    /**
     * Casts provided exception to {@link T}.
     *
     * @param e exception to check
     * @throws UnsupportedExceptionTypeException if provided exception is not supported ({@link #supports} returned false)
     */
    protected T cast(Throwable e) throws UnsupportedExceptionTypeException {
        if (supports(e.getClass())) {
            return supportedExceptionClass.cast(e);
        }
        throw new UnsupportedExceptionTypeException(
                String.format("Only %s is supported. Provided - %s", supportedExceptionClass.getName(), e.getClass().getName()));
    }
}
