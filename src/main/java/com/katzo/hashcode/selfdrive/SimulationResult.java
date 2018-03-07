package com.katzo.hashcode.selfdrive;

import com.katzo.hashcode.HashCodeIO;

import java.util.*;
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

    public Map<Integer, List<Integer>> getVehicleRides() {
        return Collections.unmodifiableMap(vehicleRides);
    }

    public long getScore() {
        Score score = new Score(simulation, this);

        return score.getScore();
    }

    private static class Score {

        private final Simulation simulation;
        private SimulationResult simulationResult;

        public Score(Simulation simulation, SimulationResult simulationResult) {
            this.simulation = simulation;
            this.simulationResult = simulationResult;
        }

        public boolean isSimulationResultValid() {
            boolean valid = true;
            Map<Integer, List<Integer>> vehicleRides = simulationResult.getVehicleRides();

            valid &= areRidesUnique(vehicleRides);

            valid &= areVehiclesRidesValid(vehicleRides);

            return valid;
        }

        public long getScore() {
            if (!isSimulationResultValid()) {
                return 0;
            }

            Map<Integer, List<Integer>> vehicleRides = simulationResult.getVehicleRides();
            long score = vehicleRides.values()
                    .stream()
                    .mapToLong(this::ridesScore)
                    .sum();

            return score;
        }

        private long ridesScore(List<Integer> vehicleRides) {
            long score = 0;
            Position pos = new Position();
            int actualTime = 0;
            int rideStartTime = 0;
            int journeyTime;
            List<Ride> rides = simulation.getRides();

            for (int i = 0; i < vehicleRides.size(); i++) {
                Integer vehicleRide = vehicleRides.get(i);
                Ride ride = rides.get(vehicleRide);
                int distanceTo = ride.getStartPosition().getDistanceTo(pos);
                actualTime += distanceTo;

                rideStartTime = actualTime;
                // If the vehicle arrives to early it needs to wait
                if (actualTime < ride.getStartTime()) {
                    rideStartTime = ride.getStartTime();
                }

                journeyTime = rideStartTime + ride.getDistance();

                // Each ride completed before its latest finish earns the number of points equal to the distance between
                // the start intersection and the finish intersection.
                if (journeyTime < ride.getEndTime()) {
                    score += ride.getDistance();
                }

                // Additionally, each ride which started exactly in its earliest allowed start step gets an additional
                // timeliness bonus
                if (actualTime <= ride.getStartTime()) {
                    score += simulation.getBonus();
                }

                pos = ride.getEndPosition();
                actualTime = journeyTime;
            }

            return score;
        }


        boolean areRidesUnique(Map<Integer, List<Integer>> vehicleRides) {
            Set<Integer> set = new HashSet<>();

            for (List<Integer> rides : vehicleRides.values()){
                for(Integer ride : rides) {
                    if (!set.add(ride))
                        return false;
                }
            }

            return true;
        }

        boolean areVehiclesRidesValid(Map<Integer, List<Integer>> vehicleRides) {
            return vehicleRides.entrySet()
                    .stream()
                    .allMatch(vehicleRide -> areVehicleRidesValid(vehicleRide.getKey(), vehicleRide.getValue()));
        }

        boolean areVehicleRidesValid(Integer vehicle, List<Integer> vehicleRides) {
            boolean valid = true;
            Position pos = new Position();
            int actualTime = 0;
            int latestDropOffTime = 0;
            List<Ride> rides = simulation.getRides();

            for (int i = 0; i < vehicleRides.size() && valid; i++) {
                Integer vehicleRide = vehicleRides.get(i);
                Ride ride = rides.get(vehicleRide);
                int distanceTo = ride.getStartPosition().getDistanceTo(pos);
                actualTime += distanceTo;

                valid &= actualTime <= ride.getEndTime();
                if (!valid) {
                    System.err.println("ERROR vehicle = " + vehicle + " arrive to late, actualTime = " + actualTime + " ride = " + ride);
                }

                if (actualTime < ride.getStartTime()) {
                    actualTime = ride.getStartTime();
                }

                int travelTimeEnd = actualTime + ride.getDistance();

                valid &= latestDropOffTime <= travelTimeEnd;
                if (!valid) {
                    System.err.println("ERROR last drop off time was at = " + latestDropOffTime + " ride = " + ride + " is not after previous ride = " + rides.get(vehicleRides.get(i - 1)));
                }

                pos = ride.getEndPosition();
                actualTime = travelTimeEnd;
                latestDropOffTime = travelTimeEnd;
            }

            if (!valid) {
                System.out.println("rides = " + vehicleRides);
            }

            return valid;
        }
    }
}
