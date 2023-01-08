package fr.lernejo.navy_battle;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

public class Utils {

    static Map<String, Object> createResponse(String consequence, boolean shipLeft) {
        Map<String, Object> response = new HashMap<>();
        response.put("consequence", consequence);
        response.put("shipLeft", shipLeft);
        return response;
    }

    static void sendResponse(HttpExchange exchange, int statusCode, Object response) throws IOException {
        byte[] responseBytes = response.toString().getBytes();
        exchange.sendResponseHeaders(statusCode, responseBytes.length);
        OutputStream os = exchange.getResponseBody();
        os.write(responseBytes);
        os.close();
    }


}
