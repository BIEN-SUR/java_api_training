package fr.lernejo.navy_battle;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;

public class Launcher
{
    public static void main(String[] args) throws IOException {
        if (args.length > 0)
        {
            int port = Integer.parseInt(args[0]);
            if (port <= 0) {
                throw new IllegalArgumentException("Port should be positive");
            }
            System.out.println("Starting server on port " + args[0] + "...");
            HttpServer server = HttpServerUtil.createAndStartServer(port);
        }
        else {
            throw new IllegalArgumentException("Please provide a port");
        }
    }
}
