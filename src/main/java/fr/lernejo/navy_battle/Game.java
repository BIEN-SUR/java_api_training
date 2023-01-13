package fr.lernejo.navy_battle;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class Game {
    public final Json Adversary;
    public final fr.lernejo.navy_battle.Json json;

    public Game(fr.lernejo.navy_battle.Json testMessage, fr.lernejo.navy_battle.Json testMessage1) {
        this.Adversary = testMessage;
        this.json = testMessage1;
    }

    public void initGame() throws IOException, InterruptedException {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create(Adversary.url.substring(1, Adversary.url.length() - 1) + "/api/game/fire?cell=A1"))
            .setHeader("Accept", "application/json").setHeader("Content-Type", "application/json").GET().build();
        HttpResponse<String> send = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        System.out.println(send.body());
    }


}
