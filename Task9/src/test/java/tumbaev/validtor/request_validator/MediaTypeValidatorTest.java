package tumbaev.validtor.request_validator;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpPrincipal;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tumbaev.backbone.util.constant.HttpHeader;
import tumbaev.backbone.util.constant.Mime;
import tumbaev.exception.UnsupportedMediaTypeException;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URI;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MediaTypeValidatorTest {
    private final MediaTypeValidator mediaTypeValidator = new MediaTypeValidator();

    @Test
    @DisplayName("Should not throw exception when Content-type contains application/json and Accept is not present")
    void testShouldNotThrowExceptionWhenContentTypeContainsApplicationJsonAndAcceptIsNotPresent() {
        Headers headers = new Headers();
        headers.add(HttpHeader.CONTENT_TYPE.getName(), Mime.APPLICATION_JSON.getName());
        assertDoesNotThrow(() -> mediaTypeValidator.validate(getMockHttpExchange(headers)));
    }

    @Test
    @DisplayName("Should not throw exception when Content-type contains application/json and Accept contains application/json")
    void testShouldNotThrowExceptionWhenContentTypeContainsApplicationJsonAndAcceptContainsApplicationJson() {
        Headers headers = new Headers();
        headers.add(HttpHeader.CONTENT_TYPE.getName(), Mime.APPLICATION_JSON.getName());
        headers.add(HttpHeader.ACCEPT.getName(), Mime.APPLICATION_JSON.getName());
        assertDoesNotThrow(() -> mediaTypeValidator.validate(getMockHttpExchange(headers)));
    }

    @Test
    void testShouldThrowExceptionWhenContentTypeIsNotPresent() {
        Headers headers = new Headers();
        assertThrows(UnsupportedMediaTypeException.class, () -> mediaTypeValidator.validate(getMockHttpExchange(headers)));
    }

    @Test
    void testShouldThrowExceptionWhenContentTypeIsNotApplicationJson() {
        Headers headers = new Headers();
        headers.add(HttpHeader.CONTENT_TYPE.getName(), Mime.TEXT_PLAIN.getName());
        assertThrows(UnsupportedMediaTypeException.class, () -> mediaTypeValidator.validate(getMockHttpExchange(headers)));
    }

    @Test
    void testShouldThrowExceptionWhenAcceptPresentAndIsNotApplicationJson() {
        Headers headers = new Headers();
        headers.add(HttpHeader.ACCEPT.getName(), Mime.TEXT_PLAIN.getName());
        assertThrows(UnsupportedMediaTypeException.class, () -> mediaTypeValidator.validate(getMockHttpExchange(headers)));
    }

    /**
     * Creates mock http exchange, that only have {@link HttpExchange#getRequestHeaders()} implemented.
     * Other methods will return null.
     *
     * @param requestHeaders request headers that will be returned by getRequestHeaders()
     * @return mock http exchange that only have getRequestHeaders() implemented
     */
    private HttpExchange getMockHttpExchange(Headers requestHeaders) {
        return new HttpExchange() {
            @Override
            public Headers getRequestHeaders() {
                return requestHeaders;
            }

            @Override
            public Headers getResponseHeaders() {
                return null;
            }

            @Override
            public URI getRequestURI() {
                return null;
            }

            @Override
            public String getRequestMethod() {
                return null;
            }

            @Override
            public HttpContext getHttpContext() {
                return null;
            }

            @Override
            public void close() {

            }

            @Override
            public InputStream getRequestBody() {
                return null;
            }

            @Override
            public OutputStream getResponseBody() {
                return null;
            }

            @Override
            public void sendResponseHeaders(int rCode, long responseLength) {

            }

            @Override
            public InetSocketAddress getRemoteAddress() {
                return null;
            }

            @Override
            public int getResponseCode() {
                return 0;
            }

            @Override
            public InetSocketAddress getLocalAddress() {
                return null;
            }

            @Override
            public String getProtocol() {
                return null;
            }

            @Override
            public Object getAttribute(String name) {
                return null;
            }

            @Override
            public void setAttribute(String name, Object value) {

            }

            @Override
            public void setStreams(InputStream i, OutputStream o) {

            }

            @Override
            public HttpPrincipal getPrincipal() {
                return null;
            }
        };
    }
}