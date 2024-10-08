package handlers;

import data.RequestData;
import data.ResponseHistoryManager;
import data.ResponseBuilder;
import exceptions.InvalidRequestException;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

public class RequestHandler {
    private final RequestParser requestParser = new RequestParser();
    private final ResponseBuilder responseBuilder = new ResponseBuilder();
    private final ResponseHistoryManager responseHistoryManager = ResponseHistoryManager.getInstance();

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
            long start = System.nanoTime();
            RequestData requestData = requestParser.parseQuery(queryString);
            boolean flag = GeometryValidator.isInsideArea(requestData.getX(), requestData.getY(), requestData.getR());
            long end = System.nanoTime();
            long elapsed= end - start;
            DateTimeFormatter customFormatter = DateTimeFormatter.ofPattern("hh:mm:ss.SSSXXX");
            String formattedDateCustom = ZonedDateTime.now().format(customFormatter);
            String response = responseBuilder.buildSuccessResponse(requestData, flag, elapsed, formattedDateCustom);
            responseHistoryManager.addRequest(responseBuilder.buildJson(requestData, flag, elapsed, formattedDateCustom));
            System.out.println(response);
        } catch (InvalidRequestException e) {
            System.out.println(responseBuilder.buildErrorResponse("Invalid request data."));
        }
    }

    private void handleGetRequest(String queryString) {
        System.out.println(responseBuilder.buildHistoryResponse(responseHistoryManager.getRequestHistoryJSON()));
    }

    private void handlePutRequest(String queryString) {
        System.out.println("PUT request received with query: " + queryString);
    }
}