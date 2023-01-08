package fr.lernejo.navy_battle;

import java.io.IOException;
import java.net.URL;

public class Launcher
{
    public static void main(String[] args) throws IOException, InterruptedException {
        int port = Integer.parseInt(args[0]);
        if (port <= 0) throw new IllegalArgumentException("Invalid port");
        System.out.println("Starting server on port " + args[0] + "...");
        try {
            Server.start(port);
            System.out.println("Server started successfully !");
            if (args.length == 2) {
                URL url = new URL(args[1]);
                int clientPort = url.getPort();
                String adversaryUrl = args[1];
                Server.start(clientPort);
                Client.post(adversaryUrl, port, "Test message"); }
        } catch (IOException e) { e.printStackTrace(); }
    }
}
