package fr.lernejo.navy_battle;

import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;
import org.junit.jupiter.api.Test;

class PostTest {

        @Test
        void postTest() throws IOException, InterruptedException {
            int port = 1254;
            Launcher.main(new String[]{String.valueOf(port)});
            String serverUrl = "http://localhost:" + port;
            Client.post(serverUrl, port, "Test message");
        }

        @Test
        void postTest2() throws IOException, InterruptedException {
            int port = 7896;
            Launcher.main(new String[]{String.valueOf(port)});
            String serverUrl = "http://localhost:" + port;
            Client.post(serverUrl, port, "Test message");
        }
}

