package com.katzo.hashcode.selfdrive;

/**
 * Created by pswiatowski on 3/1/18.
 */
public class Journey {

    private Ride ride;
    private Vehicle vehicle;

    private int score;
    private int totalTIme;

    public Journey(Ride ride, Vehicle vehicle, int score, int time) {
        this.ride = ride;
        this.vehicle = vehicle;
        this.score = score;
        this.totalTIme = time;
    }

    public Ride getRide() {
        return ride;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public int getTotalTIme() {
        return totalTIme;
    }

    public double calculate() {
        return score / totalTIme;
    }
}
