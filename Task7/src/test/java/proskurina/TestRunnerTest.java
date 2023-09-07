package proskurina;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import proskurina.exceptions.IllegalTestClassException;

import java.util.Arrays;
import java.util.List;


/**
 * Класс, тестирующий работу аннотаций в {@link TestRunner},
 * использует специальные классы, созданные для каждого тестового кейса.
 *
 * @see MultipleBeforeAllMethodCase
 * @see MultipleAfterAllMethodCase
 * @see NoTestMethodCase
 * @see AllAnnotationsCase
 */
class TestRunnerTest {
    
    @Test
    @DisplayName("Тестируется IllegalTestClassException при нескольких @BeforeAllMethod")
    void testIllegalTestClassExceptionOnSeveralBeforeAllMethod() {
        Assertions.assertThrows(IllegalTestClassException.class,
                () -> TestRunner.start(MultipleBeforeAllMethodCase.class));
    }
    
    @Test
    @DisplayName("Тестируется IllegalTestClassException при нескольких @AfterAllMethod")
    void testIllegalTestClassExceptionOnSeveralAfterAllMethod() {
        Assertions.assertThrows(IllegalTestClassException.class,
                () -> TestRunner.start(MultipleAfterAllMethodCase.class));
    }
    
    @Test
    @DisplayName("Тестируется IllegalTestClassException при отсутствии @TestMethod")
    void testIllegalTestClassExceptionOnNoTestMethod() {
        Assertions.assertThrows(IllegalTestClassException.class,
                () -> TestRunner.start(NoTestMethodCase.class));
    }
    
    @Test
    @DisplayName("Тестируется корректность последовательности вызовов при использовании @Order")
    void testCorrectCallSequenceOnUsingOrder() {
        List<String> expectedCallSequence = Arrays.asList("beforeAll", "first", "second", "third", "afterAll");
        
        TestRunner.start(AllAnnotationsCase.class);
        
        Assertions.assertEquals(expectedCallSequence, AllAnnotationsCase.callSequence);
    }
    
}