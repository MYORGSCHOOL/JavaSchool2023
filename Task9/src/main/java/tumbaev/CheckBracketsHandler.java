package tumbaev;

import com.sun.net.httpserver.HttpExchange;
import tumbaev.backbone.AbstractHttpHandler;
import tumbaev.backbone.ResponseEntity;
import tumbaev.backbone.exception_handler.ExceptionHandlerManager;
import tumbaev.backbone.util.JsonConverter;
import tumbaev.backbone.util.constant.HttpCode;
import tumbaev.backbone.util.constant.HttpHeader;
import tumbaev.backbone.util.constant.Mime;
import tumbaev.backbone.validator.body_validator.RequestBodyValidatorManager;
import tumbaev.backbone.validator.request_validator.HttpRequestValidatorManager;
import tumbaev.dto.CheckBracketsRequest;
import tumbaev.dto.CheckBracketsResponse;
import tumbaev.exception_handler.body_handler.BlankTextExceptionHandler;
import tumbaev.exception_handler.body_handler.JsonConversionExceptionHandler;
import tumbaev.exception_handler.body_handler.TextLengthExceptionHandler;
import tumbaev.exception_handler.body_handler.BlankBodyExceptionHandler;
import tumbaev.exception_handler.request_handler.MethodNotAllowedExceptionHandler;
import tumbaev.exception_handler.request_handler.UnknownUriExceptionHandler;
import tumbaev.exception_handler.request_handler.UnsupportedMediaTypeExceptionHandler;
import tumbaev.validtor.body_validator.TextLengthValidator;
import tumbaev.validtor.body_validator.TextNotBlankValidator;
import tumbaev.validtor.request_validator.MediaTypeValidator;
import tumbaev.validtor.request_validator.PostMethodValidator;
import tumbaev.validtor.request_validator.UriValidator;

import java.util.List;
import java.util.Map;


public class CheckBracketsHandler extends AbstractHttpHandler {
    private final RequestBodyValidatorManager<CheckBracketsRequest> bodyValidator;
    private final CheckBracketsService checkBracketsService = new CheckBracketsService();

    public CheckBracketsHandler() {
        super(
                new ExceptionHandlerManager(
                        List.of(new BlankBodyExceptionHandler(),
                                new BlankTextExceptionHandler(), new TextLengthExceptionHandler(),
                                new JsonConversionExceptionHandler(), new MethodNotAllowedExceptionHandler(),
                                new UnknownUriExceptionHandler(), new UnsupportedMediaTypeExceptionHandler())
                ),
                new HttpRequestValidatorManager(
                        List.of(new UriValidator(), new PostMethodValidator(), new MediaTypeValidator())
                )
        );

        this.bodyValidator = new RequestBodyValidatorManager<>(
                List.of(new TextNotBlankValidator(), new TextLengthValidator()),
                CheckBracketsRequest.class
        );
    }

    @Override
    protected ResponseEntity process(HttpExchange exchange) {
        CheckBracketsRequest request = bodyValidator.validateBodyOf(exchange);
        CheckBracketsResponse response = checkBracketsService.checkBrackets(request);

        return ResponseEntity
                .code(HttpCode.OK)
                .headers(Map.of(HttpHeader.CONTENT_TYPE, List.of(Mime.APPLICATION_JSON.getName())))
                .body(JsonConverter.toJson(response))
                .build();
    }
}
