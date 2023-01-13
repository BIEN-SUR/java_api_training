package fr.lernejo.navy_battle.FireApi;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.json.JSONObject;
import java.io.IOException;
import java.io.OutputStream;

public class FireHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        int code = 400;
        String body = "Bad Request";
        if ("GET".equals(exchange.getRequestMethod())) {
            code = 202;
            body = createBody();
        } else if (!"GET".equals(exchange.getRequestMethod())) {
            code = 404;
        }
        exchange.getResponseHeaders().set("Content-Type", "application/json");
        exchange.sendResponseHeaders(code, body.length());
        try (OutputStream outputStream = exchange.getResponseBody()) {
            outputStream.write(body.getBytes());
        }
        System.out.println(body + code);
    }
    public String createBody() {
        JSONObject object = new JSONObject();
        object.put("consequence", "sunk");
        object.put("shipLeft", "true");
        return object.toString();
    }

}






//public class FireHandler implements HttpHandler {
//
//    @Override
//    public void handle(HttpExchange exchange) throws IOException {
//        if (!"GET".equals(exchange.getRequestMethod())) {
//            Utils.sendResponse(exchange, 400, "Bad request method");
//            return;
//        }
//
//        String cell = getQueryParam(exchange, "cell");
//        if (cell == null) {
//            sendResponse(exchange, 400, "Missing cell parameter");
//            return;
//        }
//
//        Map<String, Object> response = handleFireRequest(cell);
//        sendResponse(exchange, 200, response);
//    }
//
//    private static String getQueryParam(HttpExchange exchange, String paramName) {
//        Map<String, String> params = QueryString.parse(exchange.getRequestURI().getQuery());
//        return params.get(paramName);
//    }
//
//    private static Map<String, Object> handleFireRequest(String cell) {
//        int row = getRow(cell);
//        int col = getColumn(cell);
//        return createResponse("miss", true);
//    }
//
//
//    private static int getRow(String cell) {
//        char row = cell.charAt(0);
//        return row - 'A';
//    }
//
//    private static int getColumn(String cell) {
//        char col = cell.charAt(1);
//        return col - '1';
//    }
//
//}
