package fr.lernejo.navy_battle;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

public class StartServer {

    public static void start(int port) throws IOException {
        // Création du serveur
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        // Configuration de l'executor avec un seul thread
        server.setExecutor(Executors.newFixedThreadPool(1));
        // Association d'un gestionnaire de requête au chemin /ping
        server.createContext("/ping", new PingHandler());
        server.createContext("/api/game/start", new StartHandler());
        // Démarrage du serveur
        server.start();
    }

}
