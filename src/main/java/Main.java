
import com.fastcgi.FCGIInterface;
import handlers.RequestHandler;

public class Main {
    public static void main(String[] args) {

        FCGIInterface intf = new FCGIInterface();
        RequestHandler requestHandler = new RequestHandler();

        while (intf.FCGIaccept() >= 0) {
            requestHandler.handleRequest(FCGIInterface.request.params);
        }
    }
}