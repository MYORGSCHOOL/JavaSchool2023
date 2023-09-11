package tumbaev.backbone;

import tumbaev.backbone.util.constant.HttpCode;
import tumbaev.backbone.util.constant.HttpHeader;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Represents information that should be sent to a client after response was processed.
 */
public class ResponseEntity {
    private final HttpCode code;
    private final Map<HttpHeader, List<String>> headers;
    private final String body;

    private ResponseEntity(HttpCode code, Map<HttpHeader, List<String>> headers, String body) {
        this.code = code;
        this.headers = headers;
        this.body = body;
    }

    public static ResponseEntityBuilder code(HttpCode code) {
        return new ResponseEntityBuilder(code);
    }

    public HttpCode getCode() {
        return code;
    }

    public Optional<Map<HttpHeader, List<String>>> getHeaders() {
        return Optional.ofNullable(headers);
    }

    public Optional<String> getBody() {
        return Optional.ofNullable(body);
    }


    public static class ResponseEntityBuilder {
        private final HttpCode code;
        private Map<HttpHeader, List<String>> headers = null;
        private String body = null;

        ResponseEntityBuilder(HttpCode code) {
            this.code = code;
        }

        public ResponseEntityBuilder headers(Map<HttpHeader, List<String>> headers) {
            this.headers = headers;
            return this;
        }

        public ResponseEntityBuilder body(String body) {
            this.body = body;
            return this;
        }

        public ResponseEntity build() {
            return new ResponseEntity(this.code, this.headers, this.body);
        }
    }
}
