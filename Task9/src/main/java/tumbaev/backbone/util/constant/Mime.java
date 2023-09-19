package tumbaev.backbone.util.constant;

public enum Mime {
    APPLICATION_JSON("application/json"),
    TEXT_PLAIN("text/plain");

    private final String name;

    Mime(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
