package grossu.testannotation;


import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.Documented;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.ElementType;
/**
 * Аннотация помечающая класс, как тест
 * Может учитывать приоритеты
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface TestMethod {
int order() default Integer.MAX_VALUE;
}
