package tumbaev.validtor.body_validator;

import tumbaev.backbone.validator.body_validator.RequestBodyValidator;
import tumbaev.dto.CheckBracketsRequest;
import tumbaev.exception.BlankTextException;

public class TextNotBlankValidator implements RequestBodyValidator<CheckBracketsRequest> {

    /**
     * Validates the CheckBracketsRequest by checking if the provided text is null,
     * empty, or contains only whitespace characters.
     *
     * @param body the CheckBracketsRequest to be validated
     * @return the validated CheckBracketsRequest
     * @throws BlankTextException provided text is null, empty, or contains only whitespace characters.
     */
    @Override
    public CheckBracketsRequest validate(CheckBracketsRequest body) {
        if (body.getText() == null || body.getText().isBlank()) {
            throw new BlankTextException("Provided text was null, empty or contained only whitespace characters");
        }
        return body;
    }
}
