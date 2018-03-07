package com.katzo.hashcode.selfdrive;

public class Position {

    private final int x;
    private final int y;

    public Position(){
        this.x = 0;
        this.y = 0;
    }
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

    @Override
    public String toString() {
        return "Position{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
