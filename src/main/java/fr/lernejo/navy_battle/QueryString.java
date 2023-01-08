package fr.lernejo.navy_battle;

import java.util.HashMap;
import java.util.Map;

public class QueryString {
    public static Map<String, String> parse(String queryString) {
        Map<String, String> params = new HashMap<>();
        if (queryString != null) {
            String[] pairs = queryString.split("&");
            for (String pair : pairs) {
                int idx = pair.indexOf("=");
                String key = pair.substring(0, idx);
                String value = pair.substring(idx + 1);
                params.put(key, value);
            }
        }
        return params;
    }
}
