package tumbaev.backbone.exception_handler;

import tumbaev.backbone.ResponseEntity;
import tumbaev.backbone.util.constant.HttpCode;
import tumbaev.backbone.util.constant.HttpHeader;
import tumbaev.backbone.util.constant.Mime;

import java.util.List;
import java.util.Map;
import java.util.logging.Level;

/**
 * Handles exceptions if no handler was provided for the occurred exception.
 *
 * @see ExceptionHandlerManager
 */
public class DefaultExceptionHandler extends AbstractExceptionHandler<Throwable> {

    /**
     * Returns Response entity with:
     * <ul>
     *     <li>code - INTERNAL_SERVER_ERROR</li>
     *     <li>header - Content-type: application/json</li>
     *     <li>body - json of {@link ErrorResponse}</li>
     * </ul>
     *
     * @param e exception to handle
     * @return response entity with internal server error code and body
     */
    @Override
    public ResponseEntity handle(Throwable e) {
        ErrorResponse response = new ErrorResponse(
                HttpCode.INTERNAL_SERVER_ERROR,
                "Something went wrong",
                e.getMessage()
        );
        logger.log(Level.SEVERE, "Internal server error: " + e.getMessage(), e);

        return ResponseEntity
                .code(response.getCode())
                .headers(Map.of(HttpHeader.CONTENT_TYPE, List.of(Mime.APPLICATION_JSON.getName())))
                .body(errorResponseToJson(response))
                .build();
    }
}
