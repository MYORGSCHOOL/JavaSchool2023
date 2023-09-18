package tumbaev.validator.body_validator;

import org.junit.jupiter.api.Test;
import tumbaev.dto.CheckBracketsRequest;
import tumbaev.exception.BlankTextException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TextNotBlankValidatorTest {
    private final TextNotBlankValidator textNotBlankValidator = new TextNotBlankValidator();

    @Test
    void testShouldNotThrowExceptionWhenTextNotBlank() {
        assertDoesNotThrow(() -> textNotBlankValidator.validate(new CheckBracketsRequest("Hello world")));
    }

    @Test
    void testShouldThrowExceptionWhenTextIsNull() {
        assertThrows(BlankTextException.class, () -> textNotBlankValidator.validate(new CheckBracketsRequest(null)));
    }

    @Test
    void testShouldThrowExceptionWhenTextIsBlank() {
        assertThrows(BlankTextException.class, () -> textNotBlankValidator.validate(new CheckBracketsRequest("   ")));
    }
}