package tumbaev.backbone.util.constant;

public enum HttpHeader {
    CONTENT_TYPE("Content-Type"),
    ACCEPT("Accept");

    private final String name;

    HttpHeader(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
