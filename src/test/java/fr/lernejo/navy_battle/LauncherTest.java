package fr.lernejo.navy_battle;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.junit.Assert.*;

public class LauncherTest {

    @Test
    public void testLauncherWithOneArgument() throws IOException, InterruptedException {
        String[] args = {"8888"};
        try {
            Launcher.main(args);
            assertTrue(true);
        } catch (IllegalArgumentException e) {
            fail("Invalid argument should not be thrown with port 8080");
        }
    }
    @Test
    public void testLauncherWithTwoArguments() throws IOException, InterruptedException {
        String[] args = {"9080", "http://localhost:8888"};
        try {
            Launcher.main(args);
            assertTrue(true);
        } catch (IllegalArgumentException e) {
            fail("Invalid argument should not be thrown with port 9080 and url http://localhost:8888");
        }
    }
    @Test
    public void testLauncherWithInvalidPort() throws IOException, InterruptedException {
        String[] args = {"-8280", "http://localhost:8080"};
        try {
            Launcher.main(args);
            fail("Invalid argument should be thrown with negative port number");
        } catch (IllegalArgumentException e) {
            assertEquals("Invalid port", e.getMessage());
        }
    }

    @Test
    public void TestTooManyArgs() throws IOException, InterruptedException {
        String[] args = {"8080", "http://localhost:8080", "test"};
        try {
            Launcher.main(args);
            fail("Invalid argument should be thrown with too many arguments");
        } catch (IllegalArgumentException e) {
            assertEquals("Invalid arguments", e.getMessage());
        }
    }

    @Test
    public void pingTest() throws Exception {
        Launcher.main(new String[]{"36"});
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("http://localhost:36/ping")).GET().build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        Assertions.assertThat(response.statusCode()).isEqualTo(200);
        Assertions.assertThat(response.body()).isEqualTo("OK");
    }

    @Test
    public void NoArgs() {
        try {
            Launcher.main(new String[]{});
            fail("Invalid argument should be thrown with no arguments");
        } catch (IllegalArgumentException | IOException | InterruptedException e) {
            assertEquals("Invalid arguments", e.getMessage());
        }
    }
}

