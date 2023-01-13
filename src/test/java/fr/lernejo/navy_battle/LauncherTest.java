package fr.lernejo.navy_battle;

import org.junit.Test;

import java.io.IOException;

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
}

