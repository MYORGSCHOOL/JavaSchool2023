package malakhova;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface BeforeAllMethod {
    String order() default "before";
}
