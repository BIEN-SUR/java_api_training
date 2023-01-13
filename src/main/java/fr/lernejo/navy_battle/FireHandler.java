package fr.lernejo.navy_battle;

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

