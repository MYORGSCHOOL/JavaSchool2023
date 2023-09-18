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
import tumbaev.backbone.validator.request_validator.HttpRequestValidator;
import tumbaev.dto.CheckBracketsRequest;
import tumbaev.dto.CheckBracketsResponse;
import tumbaev.exception_handler.BlankBodyExceptionHandler;
import tumbaev.exception_handler.BlankTextExceptionHandler;
import tumbaev.exception_handler.JsonConversionExceptionHandler;
import tumbaev.exception_handler.TextLengthExceptionHandler;
import tumbaev.validator.request_validator.MediaTypeValidator;
import tumbaev.validator.request_validator.PostMethodValidator;
import tumbaev.validator.request_validator.UriValidator;

import java.util.List;
import java.util.Map;


public class CheckBracketsHandler extends AbstractHttpHandler {
    private final RequestBodyValidatorManager<CheckBracketsRequest> bodyValidator;
    private final CheckBracketsService checkBracketsService = new CheckBracketsService();

    public CheckBracketsHandler() {
        super(new ExceptionHandlerManager(List.of(
                new BlankBodyExceptionHandler(),
                new BlankTextExceptionHandler(),
                new TextLengthExceptionHandler(),
                new JsonConversionExceptionHandler())
        ));

        HttpRequestValidator uriValidator = new UriValidator();
        HttpRequestValidator methodValidator = new PostMethodValidator();
        HttpRequestValidator mediaTypeValidator = new MediaTypeValidator();

        uriValidator.setNextValidator(methodValidator);
        methodValidator.setNextValidator(mediaTypeValidator);

        this.setFirstValidator(uriValidator);


        this.bodyValidator = new RequestBodyValidatorManager<>(List.of(), CheckBracketsRequest.class);
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
