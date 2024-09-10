package handlers;

import data.RequestData;
import exceptions.InvalidRequestException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RequestParser {
    private static final Pattern REQUEST_PATTERN = Pattern.compile("x=[+-]?([0-9]*[.])?[0-9]+&y=[+-]?[0-9]+&r=[+-]?[0-9]+");

    public RequestData parseQuery(String queryString) throws InvalidRequestException {
        if (queryString == null) {
            throw new InvalidRequestException("Query string is null.");
        }

        Matcher matcher = REQUEST_PATTERN.matcher(queryString);
        if (!matcher.find()) {
            throw new InvalidRequestException("Invalid query format.");
        }

        String[] parts = matcher.group().split("&");
        float x = Float.parseFloat(parts[0].split("=")[1]);
        int y = Integer.parseInt(parts[1].split("=")[1]);
        int r = Integer.parseInt(parts[2].split("=")[1]);

        return new RequestData(x, y, r);
    }
}