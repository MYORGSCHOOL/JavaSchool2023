package tumbaev.backbone.util.constant;

public enum HttpCode {
    OK(200),
    BAD_REQUEST(400),
    NOT_FOUND(404),
    METHOD_NOT_ALLOWED(405),
    UNSUPPORTED_MEDIA_TYPE(415),
    INTERNAL_SERVER_ERROR(500);

    private final int code;

    HttpCode(int code) {
        this.code = code;
    }

    public int asInt() {
        return code;
    }
}
