package fr.lernejo.navy_battle;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import static fr.lernejo.navy_battle.Utils.createResponse;
import static fr.lernejo.navy_battle.Utils.sendResponse;

public class FireHandler implements HttpHandler {
    private static boolean[][] gameGrid;
    private static List<Ship> ships;
    
    public static void setGameGrid(boolean[][] gameGrid) {
        FireHandler.gameGrid = gameGrid;
    }

    public static void setShips(List<Ship> ships) {
        FireHandler.ships = ships;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if (!"GET".equals(exchange.getRequestMethod())) {
            sendResponse(exchange, 400, "Bad request method");
            return;
        }

        String cell = getQueryParam(exchange, "cell");
        if (cell == null) {
            sendResponse(exchange, 400, "Missing cell parameter");
            return;
        }

        Map<String, Object> response = handleFireRequest(cell);
        sendResponse(exchange, 200, response);
    }

    private static String getQueryParam(HttpExchange exchange, String paramName) {
        Map<String, String> params = QueryString.parse(exchange.getRequestURI().getQuery());
        return params.get(paramName);
    }

    private static Map<String, Object> handleFireRequest(String cell) {
        int row = getRow(cell);
        int col = getColumn(cell);
        if (gameGrid[row][col]) return createResponse("miss", true);
        gameGrid[row][col] = true;
        for (Ship ship : ships) {
            if (ship.isHit(row, col)) {
                    System.out.println("dinguerie");
                }
            }
        return createResponse("miss", true);
    }




    private static int getRow(String cell) {
        char row = cell.charAt(0);
        return row - 'A';
    }

    private static int getColumn(String cell) {
        char col = cell.charAt(1);
        return col - '1';
    }

}
