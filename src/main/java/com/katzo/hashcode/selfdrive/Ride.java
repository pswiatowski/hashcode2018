package com.katzo.hashcode.selfdrive;

public class Ride {

    private final Position startPosition;
    private final Position endPosition;
    private final int startTime;
    private final int endTime;

    public Position getStartPosition() {
        return startPosition;
    }

    public Position getEndPosition() {
        return endPosition;
    }

    public int getStartTime() {
        return startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public int getDistance() {
        return Math.abs(startPosition.getX() -endPosition.getX()) +
                Math.abs(startPosition.getY() -endPosition.getY());
    }

    public int getLatestPickUpTime() {
        return endTime - getDistance();
    }

    public Ride(Position startPosition, Position endPosition, int startTime, int endTime) {

        this.startPosition = startPosition;
        this.endPosition = endPosition;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
