package com.katzo.hashcode.selfdrive;

public class Ride {
    private int id;

    private final Position startPosition;
    private final Position endPosition;
    private final int startTime;
    private final int endTime;

    public Position getStartPosition() {
        return startPosition;
    }

    public Ride(int id, Position startPosition, Position endPosition, int startTime, int endTime) {

        this.id = id;
        this.startPosition = startPosition;
        this.endPosition = endPosition;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Position getEndPosition() {
        return endPosition;
    }

    public int getStartTime() {
        return startTime;
    }

    public int getDistance() {
        return startPosition.getDistanceTo(endPosition);
    }

    public int getLatestPickUpTime() {
        return endTime - getDistance();
    }

    public int getEndTime() {
        return endTime;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Ride{" +
                "id=" + id +
                ", startPosition=" + startPosition +
                ", endPosition=" + endPosition +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}
