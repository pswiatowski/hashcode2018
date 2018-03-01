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

    public void setRide(Ride ride) {
        this.ride = ride;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getTotalTIme() {
        return totalTIme;
    }

    public void setTotalTIme(int totalTIme) {
        this.totalTIme = totalTIme;
    }

    public double calculate() {
        if (totalTIme == 0) return 0;

        return score / totalTIme;
    }
}
