package com.katzo.hashcode.selfdrive;

public class Vehicle {
    private int id;
    private Position position = new Position(0, 0);
    private boolean isUsed = false;
    private int nextStartTIme = 0;

    public Vehicle(int id) {
        this.id = id;
    }

    public Journey generateJourney(Ride ride, Simulation simulation, int maxTIme) {

        int score = 0;
        int totalTime = 0;
        int waitTime = 0;

        int latestPickUpTime = ride.getLatestPickUpTime();
        int rideStartTime = ride.getStartTime();
        int travelTimeToPickUp = position.getDistanceTo(ride.getStartPosition());
        int riderDistance = ride.getDistance();

        // can i pick up this person
        if (latestPickUpTime >= (nextStartTIme + travelTimeToPickUp)) {
            score += riderDistance;
            // can i pick up at the earliest
            if (nextStartTIme + travelTimeToPickUp <= rideStartTime) {
                score += simulation.getBonus();
            }

            if (nextStartTIme + travelTimeToPickUp < rideStartTime) {
                waitTime = rideStartTime - nextStartTIme - travelTimeToPickUp;
            }

            totalTime = riderDistance + travelTimeToPickUp + waitTime;

            return new Journey(ride, this, score, totalTime);
        } else {

            // i cannot pickup that person
            return null;
        }
    }

    public int getId() {
        return id;
    }

    public int getNextStartTIme() {
        return nextStartTIme;
    }

    public void setNextStartTIme(int nextStartTIme) {
        this.nextStartTIme = nextStartTIme;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
