package tumbaev.validator.request_validator;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpPrincipal;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URI;

import static org.junit.jupiter.api.Assertions.assertTrue;

class PostMethodValidatorTest {
    PostMethodValidator methodValidator = new PostMethodValidator();

    @Test
    void testShouldBeEmptyWhenMethodIsPost() {
        HttpExchange exchange = getMockHttpExchange("POST");
        assertTrue(methodValidator.validate(exchange).isEmpty());
    }

    @Test
    void testShouldBePresentWhenMethodInNotPost() {
        HttpExchange exchange = getMockHttpExchange("GET");
        assertTrue(methodValidator.validate(exchange).isPresent());
    }

    /**
     * Creates mock http exchange, that only have {@link HttpExchange#getRequestMethod()} implemented.
     * Other methods will return null.
     *
     * @param method value that will be returned by exchange.getRequestMethod()
     * @return mock exchange that only have getRequestMethod() implemented
     */
    private HttpExchange getMockHttpExchange(String method) {
        return new HttpExchange() {
            @Override
            public Headers getRequestHeaders() {
                return null;
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
                return method;
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