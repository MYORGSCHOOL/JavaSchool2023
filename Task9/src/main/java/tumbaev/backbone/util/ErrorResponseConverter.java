package tumbaev.backbone.util;

import tumbaev.backbone.ResponseEntity;
import tumbaev.backbone.dto.ErrorResponse;
import tumbaev.backbone.exception.JsonConversionException;
import tumbaev.backbone.util.constant.HttpHeader;
import tumbaev.backbone.util.constant.Mime;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public final class ErrorResponseConverter {
    private static final Logger logger = Logger.getLogger(ErrorResponseConverter.class.getName());

    private ErrorResponseConverter() {
        throw new UnsupportedOperationException("This is an utility class");
    }

    /**
     * Converts provided response to json string.
     * <br/>
     * If conversion fails, uses {@link ErrorResponse#toJson()}.
     *
     * @param response response to convert
     * @return json representation of response
     */
    public static String toJson(ErrorResponse response) {
        try {
            return JsonConverter.toJson(response);
        } catch (JsonConversionException e) {
            logger.severe("Failed to convert error response to json, using ErrorResponse#toJson(). Message: " + e.getMessage());
            return response.toJson();
        }
    }

    /**
     * Creates {@link ResponseEntity} with code {@link ErrorResponse#getCode()}, Content-type: application/json
     * and body json representation of provided error
     *
     * @param error the error
     * @return response entity created out of error
     */
    public static ResponseEntity toResponseEntity(ErrorResponse error) {
        return ResponseEntity.code(error.getCode())
                .body(ErrorResponseConverter.toJson(error))
                .headers(Map.of(HttpHeader.CONTENT_TYPE, List.of(Mime.APPLICATION_JSON.getName())))
                .build();
    }
}
