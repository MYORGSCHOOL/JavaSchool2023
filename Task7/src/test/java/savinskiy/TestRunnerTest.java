package savinskiy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestRunnerTest {
    @Test
    void testNormalWithoutBeforeAndAfter() {
        String expectedOutput = "Before All\nTest1\nAfterAll";

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byteArrayOutputStream.reset();

        PrintStream printStream = System.out;
        System.setOut(new PrintStream(byteArrayOutputStream));

        TestRunner.start(NormalClassWithAfterAndBefore.class);

        System.setOut(printStream);
        String actualOutput = byteArrayOutputStream.toString().trim();

        Assertions.assertEquals(expectedOutput, actualOutput);
        byteArrayOutputStream.reset();
    }

    @Test
    void testNormal() {
        assertDoesNotThrow(() -> TestRunner.start(NormalClass.class));
    }

    @Test
    void testWithExceptionBeforeAll() {
        assertThrows(Exception.class, () -> TestRunner.start(ClassBeforeAllTwoTimes.class));
    }

    @Test
    void testWithExceptionAfterAll() {
        assertThrows(Exception.class, () -> TestRunner.start(ClassAfterAllTwoTimes.class));
    }
}
