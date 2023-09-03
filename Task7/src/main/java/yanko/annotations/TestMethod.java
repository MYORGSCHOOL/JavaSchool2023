package yanko.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * класс, реализующий аннотацию тестов с возможностью задания последовательности выполнения
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface TestMethod {
    /**
     * порядковый номер в последовательности выполнения
     * @return номер
     */
    int order();
}