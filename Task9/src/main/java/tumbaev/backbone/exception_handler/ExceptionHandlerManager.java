package tumbaev.backbone.exception_handler;

import tumbaev.backbone.ResponseEntity;

import java.util.List;

/**
 * Chooses what exception handler should be invoked, depending on exception provided.
 *
 * @see AbstractExceptionHandler
 * @see DefaultExceptionHandler
 */
public class ExceptionHandlerManager {

    private final List<AbstractExceptionHandler<? extends Throwable>> handlers;
    private final DefaultExceptionHandler defaultHandler;

    /**
     * @param handlers Handlers that invoked when exceptions passed in the {@link #handle} method.
     *                 Handlers will be invoked in exact same order they are provided in the list.
     *                 If the list is empty, {@link DefaultExceptionHandler} will be used.
     */
    public ExceptionHandlerManager(List<AbstractExceptionHandler<? extends Throwable>> handlers) {
        this.handlers = handlers;
        defaultHandler = new DefaultExceptionHandler();
    }

    /**
     * Iterates through list of handlers. The first handler which supports type of passed exception will handle it.
     * If no handlers were provided, {@link DefaultExceptionHandler} will be used.
     *
     * @param e exception to handle
     * @return response entity with information that should be passed in the response.
     */
    public ResponseEntity handle(Throwable e) {
        for (AbstractExceptionHandler<? extends Throwable> handler : handlers) {
            if (handler.supports(e.getClass())) {
                return handler.handle(e);
            }
        }

        return defaultHandler.handle(e);
    }
}
