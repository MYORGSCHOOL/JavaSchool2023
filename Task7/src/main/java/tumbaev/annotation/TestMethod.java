package tumbaev.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation for test methods
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface TestMethod {
    /**
     * An order in which tests will run. The less the number, the earlier the test will run
     * <br>
     * default: {@link Integer#MAX_VALUE}
     */
    int order() default Integer.MAX_VALUE;
}
