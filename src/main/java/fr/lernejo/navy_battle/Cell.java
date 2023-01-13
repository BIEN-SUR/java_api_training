package fr.lernejo.navy_battle;

public class Cell {
    private final int row;
    private final int col;
    private boolean isOccupied;
    private boolean isFired;

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
        this.isOccupied = false;
        this.isFired = false;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void setOccupied(boolean occupied) {
        this.isOccupied = occupied;
    }

    public boolean isFired() {
        return isFired;
    }

    public void setFired(boolean fired) {
        this.isFired = fired;
    }
}
