package fr.lernejo.navy_battle;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.UUID;
import java.util.stream.Collectors;

public class StartHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if (!exchange.getRequestMethod().equals("POST")) { exchange.sendResponseHeaders(404, -1); return;}
        try { BufferedReader reader = new BufferedReader(new InputStreamReader(exchange.getRequestBody()));
            String requestBody = reader.lines().collect(Collectors.joining()); reader.close();
            JSONObject json = new JSONObject(requestBody);
            JSONObject responseJson = new JSONObject();
            responseJson.put("id", UUID.randomUUID().toString());
            responseJson.put("url", "http://localhost:" + exchange.getLocalAddress().getPort());
            responseJson.put("message", "May the best code win");
            String responseBody = responseJson.toString();
            exchange.sendResponseHeaders(202, responseBody.length());
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(responseBody.getBytes()); }
        } catch (Exception e) {
            exchange.sendResponseHeaders(400, 0);
        }
    }
}
