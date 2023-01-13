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
        Launcher.main(new String[]{"8888"});
        assertTrue(true);
    }
    @Test
    public void testLauncherWithTwoArguments() throws IOException, InterruptedException {
        Launcher.main(new String[]{"9080", "http://localhost:8888"});
        assertTrue(true);
    }
    @Test
    public void testLauncherWithInvalidPort() throws IOException, InterruptedException {
        Assertions.assertThatThrownBy(() -> Launcher.main(new String[]{"-1"}))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("Invalid port");
    }

    @Test
    public void TestTooManyArgs() throws IOException, InterruptedException {
        Assertions.assertThatThrownBy(() -> Launcher.main(new String[]{"9080", "http://localhost:8888", "test"}))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("Invalid arguments");
    }

    @Test
    public void NoArgs() {
        Assertions.assertThatThrownBy(() -> Launcher.main(new String[]{}))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("Invalid arguments");
    }
}

