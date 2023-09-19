package tumbaev.exception_handler;

import tumbaev.backbone.ResponseEntity;
import tumbaev.backbone.dto.ErrorResponse;
import tumbaev.backbone.exception.JsonConversionException;
import tumbaev.backbone.exception_handler.AbstractExceptionHandler;
import tumbaev.backbone.util.ErrorResponseConverter;
import tumbaev.backbone.util.constant.HttpCode;

public class JsonConversionExceptionHandler extends AbstractExceptionHandler<JsonConversionException> {
    @Override
    public ResponseEntity handle(Throwable e) throws JsonConversionException {
        JsonConversionException ex = cast(e);
        ErrorResponse response = new ErrorResponse(
                HttpCode.BAD_REQUEST,
                "Json conversion error",
                ex.getMessage()
        );
        logger.warning("Json conversion error: " + ex.getMessage());

        return ErrorResponseConverter.toResponseEntity(response);
    }
}
