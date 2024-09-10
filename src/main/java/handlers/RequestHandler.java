package handlers;

import data.RequestData;
import data.ResponseBuilder;
import exceptions.InvalidRequestException;

import java.util.Properties;

public class RequestHandler {
    private final RequestParser requestParser = new RequestParser();
    private final ResponseBuilder responseBuilder = new ResponseBuilder();

    public void handleRequest(Properties params) {
        if (params == null) {
            System.out.println(responseBuilder.buildErrorResponse("No parameters provided."));
            return;
        }

        String requestMethod = params.getProperty("REQUEST_METHOD");
        String queryString = params.getProperty("QUERY_STRING");

        switch (requestMethod != null ? requestMethod.toUpperCase() : "") {
            case "POST" -> handlePostRequest(queryString);
            case "GET" -> handleGetRequest(queryString);
            case "PUT" -> handlePutRequest(queryString);
            default -> System.out.println(responseBuilder.buildErrorResponse("Unsupported request method: " + requestMethod));
        }
    }

    private void handlePostRequest(String queryString) {
        try {
            RequestData requestData = requestParser.parseQuery(queryString);
            boolean flag = GeometryValidator.isInsideArea(requestData.getX(), requestData.getY(), requestData.getR());
            String response = responseBuilder.buildSuccessResponse(requestData, flag);
            System.out.println(response);
        } catch (InvalidRequestException e) {
            System.out.println(responseBuilder.buildErrorResponse("Invalid request data."));
        }
    }

    private void handleGetRequest(String queryString) {
        System.out.println("GET request received with query: " + queryString);
    }

    private void handlePutRequest(String queryString) {
        System.out.println("PUT request received with query: " + queryString);
    }
}