package com.katzo.hashcode.selfdrive;

import java.util.ArrayList;
import java.util.List;

public class Simulation {

    private final int rows;
    private final int cols;
    private final int countVehicles;
    private final int countRides;
    private final int bonus;
    private final int steps;
    private List<Ride> rides;

    public Simulation(int rows, int cols, int countVehicles, int countRides, int bonus, int steps) {
        this.rows = rows;
        this.cols = cols;
        this.countVehicles = countVehicles;
        this.countRides = countRides;
        this.bonus = bonus;
        this.steps = steps;
        this.rides = new ArrayList<>(countRides);
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public int getCountVehicles() {
        return countVehicles;
    }

    public int getCountRides() {
        return countRides;
    }

    public int getBonus() {
        return bonus;
    }

    public int getSteps() {
        return steps;
    }

    public void addRide(Ride r) {
        rides.add(r);
    }
}
