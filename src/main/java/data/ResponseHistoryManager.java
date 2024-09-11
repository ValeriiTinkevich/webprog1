package data;

import java.util.ArrayList;

public class ResponseHistoryManager {

    private static ResponseHistoryManager instance;

    private static ArrayList<String> requestHistory = new ArrayList<>();

    private ResponseHistoryManager() {
    }

    public static ResponseHistoryManager getInstance() {
        if (instance == null) {
            instance = new ResponseHistoryManager();
        }
        return instance;
    }

    public void addRequest(String response) {
        requestHistory.add(response);
    }

    public ArrayList<String> getRequestHistory() {
        return requestHistory;
    }

    public String getRequestHistoryJSON() {
        return "{ \"data\" : %s}".formatted(requestHistory);
    }
}
