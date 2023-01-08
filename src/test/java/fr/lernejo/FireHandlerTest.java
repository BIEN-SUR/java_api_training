package fr.lernejo;
import fr.lernejo.navy_battle.FireHandler;
import fr.lernejo.navy_battle.Ship;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class FireHandlerTest {
    @Test
    public void testHandleFireRequest() {
        FireHandler.setGameGrid(new boolean[][]{
            {false, false, false, false, false, false, false, false, false, false},
            {false, false, false, false, false, false, false, false, false, false},
            {false, false, false, false, false, false, false, false, false, false},
            {false, false, false, false, false, false, false, false, false, false},
            {false, false, false, false, false, false, false, false, false, false},
            {false, false, false, false, false, false, false, false, false, false},
            {false, false, false, false, false, false, false, false, false, false},
            {false, false, false, false, false, false, false, false, false, false},
            {false, false, false, false, false, false, false, false, false, false},
            {false, false, false, false, false, false, false, false, false, false}
        });
        FireHandler.setShips(List.of(
            new Ship(4, List.of("A1", "A2", "A3", "A4")),
            new Ship(3, List.of("C5", "C6", "C7")),
            new Ship(2, List.of("J9", "J10"))
        ));

        Map<String, Object> response = FireHandler.handleFireRequest("A1");
        assertEquals("hit", response.get("consequence"));
        assertTrue((Boolean) response.get("shipLeft"));

        response = FireHandler.handleFireRequest("B1");
        assertEquals("miss", response.get("consequence"));
        assertTrue((Boolean) response.get("shipLeft"));

        response = FireHandler.handleFireRequest("A2");
        assertEquals("hit", response.get("consequence"));
        assertTrue((Boolean) response.get("shipLeft"));

        response = FireHandler.handleFireRequest("A3");
        assertEquals("hit", response.get("consequence"));
        assertTrue((Boolean) response.get("shipLeft"));

        response = FireHandler.handleFireRequest("A4");
        assertEquals("sunk", response.get("consequence"));
        assertTrue((Boolean) response.get("shipLeft"));

        response = FireHandler.handleFireRequest("B4");
        assertEquals("miss", response.get("consequence"));
        assertTrue((Boolean) response.get("shipLeft"));

        response = FireHandler.handleFireRequest("C5");
        assertEquals("hit", response.get("consequence"));
        assertTrue((Boolean) response.get("shipLeft"));

        response = FireHandler.handleFireRequest("D5");
        assertEquals("miss", response.get("consequence"));
        assertTrue((Boolean) response.get("shipLeft"));
    }
}
