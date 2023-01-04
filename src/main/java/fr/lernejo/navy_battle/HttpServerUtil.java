package fr.lernejo.navy_battle;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HttpServerUtil {
    public static HttpServer createAndStartServer(int port) throws IOException {
        ExecutorService executor = Executors.newFixedThreadPool(1);
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        server.createContext("/ping", new HttpHandler() {
            @Override
            public void handle(HttpExchange exchange) throws IOException {
                String response = "OK";
                exchange.sendResponseHeaders(200, response.getBytes().length);
                try(OutputStream os = exchange.getResponseBody()) {
                    os.write(response.getBytes());
                }
            }
        });

        server.createContext("/api/game/start", new HttpHandler() {
            @Override
            public void handle(HttpExchange exchange) throws IOException {
                if (!exchange.getRequestMethod().equals("POST"))
                {
                    String response = "Not found";
                    exchange.sendResponseHeaders(404, response.getBytes().length);
                    OutputStream os = exchange.getResponseBody();
                    os.write(response.getBytes());
                    os.close();
                }
                InputStreamReader requestBodyReader = new InputStreamReader(exchange.getRequestBody());
                JSONTokener token = new JSONTokener(requestBodyReader);
                JSONObject requestBody = new JSONObject(token);
                String schema = "{"
                    + "\"$schema\": \"http://json-schema.org/schema#\","
                    + "\"type\": \"object\","
                    + "\"properties\": {"
                    + "\"id\": {"
                    + "\"type\": \"string\""
                    + "},"
                    + "\"url\": {"
                    + "\"type\": \"string\""
                    + "},"
                    + "\"message\": {"
                    + "\"type\": \"string\""
                    + "}"
                    + "},"
                    + "\"required\": ["
                    + "\"id\","
                    + "\"url\","
                    + "\"message\""
                    + "]"
                    + "}";

                try {
                    JSONObject schemaObject = new JSONObject(schema);
                    JSONObject.testValidity(schemaObject);
                } catch (JSONException e) {
                    String response = "Bad Request";
                    exchange.sendResponseHeaders(400, response.getBytes().length);
                    OutputStream os = exchange.getResponseBody();
                    os.write(response.getBytes());
                    os.close();
                }
                String responseBody = "{"
                    + "\"id\": \"2aca7611-0ae4-49f3-bf63-75bef4769028\","
                    + "\"url\": \"http://localhost:" + port + "\","
                    + "\"message\": \"May the best code win\""
                    + "}";
                exchange.sendResponseHeaders(202, responseBody.getBytes().length);
                OutputStream os = exchange.getResponseBody();
                os.write(responseBody.getBytes());
                os.close();
            }
        });
        server.setExecutor(executor);
        server.start();

        return server;
    }
}

