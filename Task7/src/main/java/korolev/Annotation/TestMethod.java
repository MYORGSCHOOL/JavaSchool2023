package korolev.Annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
/**
 * Implementation abstract {@code Test} with execution priority.
 */
public @interface TestMethod {
    int order() default Integer.MAX_VALUE;
}
