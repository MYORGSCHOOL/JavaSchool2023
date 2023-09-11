package tumbaev.validtor.request_validator;

import com.sun.net.httpserver.Authenticator;
import com.sun.net.httpserver.Filter;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpPrincipal;
import com.sun.net.httpserver.HttpServer;
import org.junit.jupiter.api.Test;
import tumbaev.exception.UnknownUriException;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UriValidatorTest {
    private final UriValidator uriValidator = new UriValidator();

    @Test
    void testShouldNotThrowExceptionWhenUriIsCorrect() {
        String uri = "my/uri";
        HttpExchange exchange = getMockHttpExchange(uri, uri);
        assertDoesNotThrow(() -> uriValidator.validate(exchange));
    }

    @Test
    void testShouldThrowExceptionWhenUrisDoNotMatch() {
        String requestUri = "request/uri";
        String contextUri = "context/uri";
        HttpExchange exchange = getMockHttpExchange(requestUri, contextUri);
        assertThrows(UnknownUriException.class, () -> uriValidator.validate(exchange));
    }


    /**
     * Creates mock http exchange. {@link HttpExchange#getRequestURI()} will return provided requestUri,
     * {@link HttpExchange#getHttpContext()}.getPath() will return httpContextPath.
     *
     * @param requestUri      request uri
     * @param httpContextPath context path
     * @return mock http exchange
     */
    private HttpExchange getMockHttpExchange(String requestUri, String httpContextPath) {
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
                try {
                    return new URI(requestUri);
                } catch (URISyntaxException e) {
                    throw new RuntimeException("Cannot create uri for mock HttpExchange", e);
                }
            }

            @Override
            public String getRequestMethod() {
                return null;
            }

            @Override
            public HttpContext getHttpContext() {
                return new HttpContext() {
                    @Override
                    public HttpHandler getHandler() {
                        return null;
                    }

                    @Override
                    public void setHandler(HttpHandler h) {

                    }

                    @Override
                    public String getPath() {
                        return httpContextPath;
                    }

                    @Override
                    public HttpServer getServer() {
                        return null;
                    }

                    @Override
                    public Map<String, Object> getAttributes() {
                        return null;
                    }

                    @Override
                    public List<Filter> getFilters() {
                        return null;
                    }

                    @Override
                    public Authenticator setAuthenticator(Authenticator auth) {
                        return null;
                    }

                    @Override
                    public Authenticator getAuthenticator() {
                        return null;
                    }
                };
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