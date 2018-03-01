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

    public int getTime() {
        return time;
    }

    public void addRide(Ride r) {
        rides.add(r);
    }

    public List<Ride> getRides() {
        return rides;
    }

    public void setRides(List<Ride> rides) {
        this.rides = rides;
    }

    public List<Vehicle> getVehicleList() {
        return vehicleList;
    }

    public void setVehicleList(List<Vehicle> vehicleList) {
        this.vehicleList = vehicleList;
    }
}
