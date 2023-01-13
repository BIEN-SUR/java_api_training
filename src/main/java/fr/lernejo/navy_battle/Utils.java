package fr.lernejo.navy_battle;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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

    public static void sendResponse(HttpExchange exchange, int statusCode, Object responseBody) throws IOException {
        try (exchange) {
            byte[] responseBytes = toJson(responseBody).getBytes();
            exchange.sendResponseHeaders(statusCode, responseBytes.length);
            exchange.getResponseHeaders().add("Content-Type", "application/json");
            exchange.getResponseBody().write(responseBytes);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    private static String toJson(Object obj) throws JsonProcessingException, JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(obj);
    }



}
