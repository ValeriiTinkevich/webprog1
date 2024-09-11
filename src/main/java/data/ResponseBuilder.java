package data;

import java.nio.charset.StandardCharsets;
import java.time.ZonedDateTime;
import java.util.ArrayList;
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

    public String buildSuccessResponse(RequestData data, boolean flag, long elapsedTime, String serverTime) {
        var content = buildJson(data, flag, elapsedTime, serverTime);
        return HTTP_RESPONSE_OK.formatted(content.getBytes(StandardCharsets.UTF_8).length, content);
    }
    public String buildJson(RequestData data, boolean flag, long elapsedTime, String serverTime) {
        return String.format(Locale.US, "{\"x\": %.3f, \"y\": %d, \"r\": %d, \"flag\": %s, \"script_time\": \"%s ns\", \"server_time\": \"%s\"}",
                data.getX(), data.getY(), data.getR(), flag, elapsedTime, serverTime);
    }

    public String buildHistoryResponse(String JSON) {
        return HTTP_RESPONSE_OK.formatted(JSON.getBytes(StandardCharsets.UTF_8).length, JSON);
    }

    public String buildErrorResponse(String error) {
        String content = CONTENT_ERROR.formatted(error);
        return HTTP_RESPONSE_ERROR.formatted(content.getBytes(StandardCharsets.UTF_8).length, content);
    }
}
