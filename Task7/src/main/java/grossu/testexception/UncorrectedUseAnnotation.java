package grossu.testexception;

/**
 * Класс для выбрасывания исключений в {@code TestRunner}
 * при неправильном использовании аннотаций из пакета {@link grossu.testannotation}
 */
public class UncorrectedUseAnnotation extends RuntimeException{
    public UncorrectedUseAnnotation(String message) {
        super(message);
    }
}
