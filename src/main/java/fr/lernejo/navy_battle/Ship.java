package fr.lernejo.navy_battle;
import java.util.List;

public class Ship {
    private final int size;
    private final List<String> cells;
    private boolean sunk;
    public Ship(int size, List<String> cells) {
        this.size = size;
        this.cells = cells;
        this.sunk = false;
    }

    public int getSize() {
        return size;
    }

    public List<String> getCells() {
        return cells;
    }

    public boolean isHit(int row, int col) {
        return cells.contains(String.format("%d-%d", row, col));
    }


    public boolean isSunk() {
        return sunk;
    }

    public void setSunk(boolean sunk) {
        this.sunk = sunk;
    }
}
