package tumbaev.validator.body_validator;

import org.junit.jupiter.api.Test;
import tumbaev.dto.CheckBracketsRequest;
import tumbaev.exception.TextLengthException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TextLengthValidatorTest {
    private static final int MAX_TEXT_LENGTH = 7000;

    private final TextLengthValidator textLengthValidator = new TextLengthValidator();

    @Test
    void testShouldNotThrowExceptionWhenTextLengthIsCorrect() {
        assertDoesNotThrow(() -> textLengthValidator.validate(new CheckBracketsRequest("Hello world")));
    }

    @Test
    void testShouldThrowExceptionWhenTextIsTooLong() {
        String text = "a".repeat(MAX_TEXT_LENGTH + 1);
        assertThrows(TextLengthException.class, () -> textLengthValidator.validate(new CheckBracketsRequest(text)));
    }

}