package com.katzo.hashcode.selfdrive;

public class Position {

    private final int x;
    private final int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getDistanceTo(Position pos) {
        return Math.abs(this.getX() - pos.getX()) + Math.abs(this.getY() - pos.getY());
    }
}
