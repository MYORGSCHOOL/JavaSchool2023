package tumbaev.exception_handler;

import tumbaev.backbone.ResponseEntity;
import tumbaev.backbone.dto.ErrorResponse;
import tumbaev.backbone.exception.UnsupportedExceptionTypeException;
import tumbaev.backbone.exception_handler.AbstractExceptionHandler;
import tumbaev.backbone.util.ErrorResponseConverter;
import tumbaev.backbone.util.constant.HttpCode;
import tumbaev.exception.TextLengthException;

public class TextLengthExceptionHandler extends AbstractExceptionHandler<TextLengthException> {
    @Override
    public ResponseEntity handle(Throwable e) throws UnsupportedExceptionTypeException {
        TextLengthException ex = cast(e);
        ErrorResponse response = new ErrorResponse(
                HttpCode.BAD_REQUEST,
                "Invalid text length",
                ex.getMessage()
        );
        logger.info("User sent request with invalid text length: " + ex.getMessage());

        return ErrorResponseConverter.toResponseEntity(response);
    }
}
