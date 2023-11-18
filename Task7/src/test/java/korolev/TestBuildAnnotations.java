package korolev;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import korolev.Exceptions.IteratingOverAnnotations;
import korolev.Exceptions.PriorityAllocationError;
import korolev.Test_Annotation.BruteForceAfterAll;
import korolev.Test_Annotation.BruteForceBeforeAll;
import korolev.Test_Annotation.PriorityException;
import korolev.Test_Annotation.SuccessfulTest;

import static korolev.BuildTest.start;

public class TestBuildAnnotations {
    @Test
    void testStart() {
        Assertions.assertDoesNotThrow(() -> start(SuccessfulTest.class));
    }

    @Test
    void testExceptionPriority() {
        Assertions.assertThrows(PriorityAllocationError.class, () -> start(PriorityException.class));
    }

    @Test
    void testBruteBeforeAll() {
        Assertions.assertThrows(IteratingOverAnnotations.class, () -> start(BruteForceBeforeAll.class));
    }

    @Test
    void testBruteAfterAll () {
        Assertions.assertThrows(IteratingOverAnnotations.class, () -> start(BruteForceAfterAll.class));
    }
}
