package data;

import java.nio.charset.StandardCharsets;
import java.util.Locale;

public class ResponseBuilder {
    private static final String HTTP_RESPONSE_OK = """
            HTTP/1.1 200 OK
            Content-Type: application/json
            Content-Length: %d

            %s
            """;

    private static final String HTTP_RESPONSE_ERROR = """
            HTTP/1.1 400 Bad Request
            Content-Type: application/json
            Content-Length: %d

            %s
            """;

    private static final String CONTENT_ERROR = "{\"Error\": %s}";

    public String buildSuccessResponse(RequestData data, boolean flag) {
        String content = String.format(Locale.US, "{\"x\": %.3f, \"y\": %d, \"r\": %d, \"flag\": %s}",
                data.getX(), data.getY(), data.getR(), Boolean.toString(flag));
        return HTTP_RESPONSE_OK.formatted(content.getBytes(StandardCharsets.UTF_8).length, content);
    }

    public String buildErrorResponse(String error) {
        String content = CONTENT_ERROR.formatted(error);
        return HTTP_RESPONSE_ERROR.formatted(content.getBytes(StandardCharsets.UTF_8).length, content);
    }
}
