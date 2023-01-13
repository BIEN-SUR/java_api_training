package fr.lernejo.navy_battle;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;

public class Json {
    public final String id;
    public final String url;
    public final String message;

    public Json(String s, String serverUrl, String testMessage) {
        this.id = s;
        this.url = serverUrl;
        this.message = testMessage;
    }
}

