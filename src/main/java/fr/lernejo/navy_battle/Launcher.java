package fr.lernejo.navy_battle;

import java.io.IOException;

public class Launcher
{
    public static void main(String[] args) throws IOException, InterruptedException {
        if (args.length == 1) {
            int port = Integer.parseInt(args[0]);
            if (port <= 0) throw new IllegalArgumentException("Invalid port");
            System.out.println("Starting server on port " + args[0] + "...");
            Server.start(port);
            System.out.println("Server started successfully !"); }
        else if (args.length >= 2) {
            int port = Integer.parseInt(args[0]);
            if (port <= 0) throw new IllegalArgumentException("Invalid port");
            String serverUrl = args[1];
            Server.start(port);
            Client.post(serverUrl, port,"Test message");

        }
    }
}
