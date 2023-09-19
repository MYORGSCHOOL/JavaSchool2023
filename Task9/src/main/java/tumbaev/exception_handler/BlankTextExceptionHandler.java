package tumbaev.exception_handler;

import tumbaev.backbone.ResponseEntity;
import tumbaev.backbone.dto.ErrorResponse;
import tumbaev.backbone.exception.UnsupportedExceptionTypeException;
import tumbaev.backbone.exception_handler.AbstractExceptionHandler;
import tumbaev.backbone.util.ErrorResponseConverter;
import tumbaev.backbone.util.constant.HttpCode;
import tumbaev.exception.BlankTextException;

public class BlankTextExceptionHandler extends AbstractExceptionHandler<BlankTextException> {
    @Override
    public ResponseEntity handle(Throwable e) throws UnsupportedExceptionTypeException {
        BlankTextException ex = cast(e);
        ErrorResponse response = new ErrorResponse(
                HttpCode.BAD_REQUEST,
                "Incorrect data in the request body",
                ex.getMessage()
        );
        logger.info("User sent incorrect data in the request body: " + ex.getMessage());

        return ErrorResponseConverter.toResponseEntity(response);
    }
}
