package com.katzo.hashcode.selfdrive;

import com.katzo.hashcode.HashCodeIO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SimulationResult {

    private Map<Integer, List<Integer>> vehicleRides = new HashMap<>();
    private HashCodeIO io;
    private Simulation simulation;

    public SimulationResult(HashCodeIO io, Simulation simulation) {
        this.io = io;
        this.simulation = simulation;
    }


    public void addRide(Integer vehicleId, Integer rideId) {
        List<Integer> rides = vehicleRides.getOrDefault(vehicleId, new ArrayList<>());
        rides.add(rideId);
        vehicleRides.put(vehicleId, rides);
    }

    public void print(HashCodeIO io) {

        int countVehicles = simulation.getCountVehicles();

        for (int i = 1; i <= countVehicles; i++) {
            if (vehicleRides.containsKey(i)) {
                List<Integer> rides = vehicleRides.get(i);
                io.print(rides.size());
                io.printf(" %s\n", rides.stream().map(id -> id.toString()).collect(Collectors.joining(" ")));
            } else {
                io.print("0\n");
            }
            io.flush();
        }
    }
}
