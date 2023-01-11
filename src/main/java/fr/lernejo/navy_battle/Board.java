package fr.lernejo.navy_battle;

public class Board {
    private final boolean[][] gameGrid;
    private final int size;

    public Board(int size) {
        this.size = size;
        this.gameGrid = new boolean[size][size];
    }

    public boolean[][] getGameGrid() {
        return gameGrid;
    }

    public int getSize() {
        return size;
    }
}
