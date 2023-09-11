package tumbaev;

import org.junit.jupiter.api.Test;
import tumbaev.dto.CheckBracketsRequest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CheckBracketsServiceTest {
    private final CheckBracketsService service = new CheckBracketsService();

    @Test
    void testNoBracesShouldBeValid() {
        CheckBracketsRequest request = new CheckBracketsRequest("Hello world");
        assertTrue(service.checkBrackets(request).isCorrect());
    }

    @Test
    void testBalancedSequenceShouldBeValid() {
        CheckBracketsRequest request = new CheckBracketsRequest("(Hello) {world}");
        assertTrue(service.checkBrackets(request).isCorrect());
    }

    @Test
    void testCorrectSequenceWithNestedBracketsShouldBeValid() {
        CheckBracketsRequest request = new CheckBracketsRequest("(Hello) {world}");
        assertTrue(service.checkBrackets(request).isCorrect());
    }

    @Test
    void testBalancedSequenceWithEmptyBracketsShouldBeInvalid() {
        CheckBracketsRequest request = new CheckBracketsRequest("(Hello) {world} []");
        assertFalse(service.checkBrackets(request).isCorrect());
    }

    @Test
    void testBalancedSequenceWithEmptyNestedBracketsShouldBeInvalid() {
        CheckBracketsRequest request = new CheckBracketsRequest("(Hello) {world{}}");
        assertFalse(service.checkBrackets(request).isCorrect());
    }

    @Test
    void testSequenceWithNoClosingBracketShouldBeInvalid() {
        CheckBracketsRequest request = new CheckBracketsRequest("(Hello");
        assertFalse(service.checkBrackets(request).isCorrect());
    }

    @Test
    void testSequenceWithNoOpeningBracketShouldBeInvalid() {
        CheckBracketsRequest request = new CheckBracketsRequest("Hello)");
        assertFalse(service.checkBrackets(request).isCorrect());
    }
}