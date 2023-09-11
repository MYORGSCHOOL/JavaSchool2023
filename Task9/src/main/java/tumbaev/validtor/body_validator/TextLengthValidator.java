package tumbaev.validtor.body_validator;

import tumbaev.backbone.validator.body_validator.RequestBodyValidator;
import tumbaev.dto.CheckBracketsRequest;
import tumbaev.exception.TextLengthException;

public class TextLengthValidator implements RequestBodyValidator<CheckBracketsRequest> {
    private static final int MAX_TEXT_LENGTH = 7000;

    /**
     * Validates length of text in CheckBracketsRequest.
     * If it's greater than then {@link #MAX_TEXT_LENGTH}, throws exception
     *
     * @param body the CheckBracketsRequest to be validated
     * @return the validated CheckBracketsRequest
     * @throws TextLengthException text is too long
     */
    @Override
    public CheckBracketsRequest validate(CheckBracketsRequest body) {
        if (body.getText().length() > MAX_TEXT_LENGTH) {
            throw new TextLengthException("Provided text was null, empty or contained only whitespace characters");
        }
        return body;
    }
}
