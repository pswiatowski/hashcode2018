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

    public SimulationResult(HashCodeIO io) {
        this.io = io;
    }


    public void addRide(Integer vehicleId, Integer rideId) {
        List<Integer> rides = vehicleRides.getOrDefault(vehicleId, new ArrayList<>());
        rides.add(rideId);
        vehicleRides.put(vehicleId, rides);
    }

    public void print(HashCodeIO io) {
        vehicleRides.forEach((car, rides) -> {
            io.print(car);
            io.printf(" %s\n", rides.stream().map(id -> id.toString()).collect(Collectors.joining(" ")));
            io.flush();
        });
    }
}
