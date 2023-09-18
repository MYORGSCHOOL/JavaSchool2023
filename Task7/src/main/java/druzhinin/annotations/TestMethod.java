package druzhinin.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Аннотация для индикации того, что метод является методом-тестом.
 *
 * @author Дружинин Артем.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface TestMethod {

    /**
     * Приоритет метода.
     * Чем меньше приоритет, тем более приоритетным является метод.
     *
     * @return Возвращает целочисленное значение, представляющее приоритет метода.
     */
    int order() default Integer.MAX_VALUE;
}
