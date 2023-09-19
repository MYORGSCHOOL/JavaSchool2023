package tumbaev.backbone.validator.body_validator;

/**
 * Validates passed object. Returns the object, if it's valid.
 *
 * @param <T> type of object
 * @see RequestBodyValidatorManager
 */
@FunctionalInterface
public interface RequestBodyValidator<T> {

    /**
     * Validates passed object. Returns the object, if it's valid.
     *
     * @param body object to validate
     * @return this object, if it's valid
     */
    T validate(T body);
}
