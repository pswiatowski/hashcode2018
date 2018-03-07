package com.katzo.hashcode.selfdrive;

import java.util.ArrayList;
import java.util.List;

public class Simulation {

    private final int rows;
    private final int cols;
    private final int countVehicles;
    private final int countRides;
    private final int bonus;
    private final int time;
    private List<Ride> rides;
    private List<Vehicle> vehicleList;

    public Simulation(int rows, int cols, int countVehicles, int countRides, int bonus, int time, List<Vehicle> vehicleList) {
        this.rows = rows;
        this.cols = cols;
        this.countVehicles = countVehicles;
        this.countRides = countRides;
        this.bonus = bonus;
        this.time = time;
        this.rides = new ArrayList<>(countRides);
        this.vehicleList = vehicleList;
    }

    public int getCountVehicles() {
        return countVehicles;
    }

    public int getBonus() {
        return bonus;
    }

    public int getTime() {
        return time;
    }

    public void addRide(Ride r) {
        rides.add(r);
    }

    public List<Ride> getRides() {
        return new ArrayList<>(rides);
    }

    public List<Vehicle> getVehicleList() {
        return vehicleList;
    }
}
