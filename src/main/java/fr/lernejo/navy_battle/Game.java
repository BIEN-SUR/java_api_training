package fr.lernejo.navy_battle;

import java.util.List;

public class Game {
    private final boolean[][] gameGrid;
    private final List<Ship> ships;

    public Game(List<Ship> ships) {
        this.ships = ships;
        this.gameGrid = new boolean[10][10];
    }

}
