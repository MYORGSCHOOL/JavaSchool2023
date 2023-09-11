package tumbaev.exception_handler.body_handler;

import tumbaev.backbone.ResponseEntity;
import tumbaev.backbone.exception_handler.ErrorResponse;
import tumbaev.backbone.exception.JsonConversionException;
import tumbaev.backbone.exception_handler.AbstractExceptionHandler;
import tumbaev.backbone.util.constant.HttpCode;
import tumbaev.backbone.util.constant.HttpHeader;
import tumbaev.backbone.util.constant.Mime;

import java.util.List;
import java.util.Map;

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

        return ResponseEntity
                .code(response.getCode())
                .headers(Map.of(HttpHeader.CONTENT_TYPE, List.of(Mime.APPLICATION_JSON.getName())))
                .body(errorResponseToJson(response))
                .build();
    }
}
