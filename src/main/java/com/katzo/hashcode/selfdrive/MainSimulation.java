package com.katzo.hashcode.selfdrive;

import com.google.common.collect.Lists;
import com.google.common.io.Files;
import com.katzo.hashcode.HashCodeIO;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

public class MainSimulation {

    public static void main(String[] args) throws IOException {
        MainSimulation mainSimulation = new MainSimulation();

        Simulation s = mainSimulation.read("2018/a_example.in");

        for (int i = 0; i < s.getTime(); i++) {
                for (Vehicle vehicle : s.getVehicleList()) {
                    List<Journey> possibleJourneys = Lists.newArrayList();
                    for(Ride ride : s.getRides()) {
                        Journey journey = vehicle.generateJourney(ride, s);
                        possibleJourneys.add(journey);
                    }

                    // choose the best one
                    possibleJourneys.get(0);
                }
        }
    }

    private Simulation read(String inputFile) throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource(inputFile);
        File file = new File(resource.getFile());
        HashCodeIO io = new HashCodeIO(Files.asByteSource(file).openStream(), System.out);

        // Read Simulation
        int rows = io.getInt();
        int cols = io.getInt();
        int vehicles = io.getInt();
        int rides = io.getInt();
        int bonus = io.getInt();
        int T = io.getInt();


        List<Vehicle> vehicleList = Lists.newArrayList();
        for (int i = 0; i <vehicles; i++) {
                vehicleList.add(new Vehicle(i));
        }

        Simulation simulation = new Simulation(rows, cols, vehicles, rides, bonus, T, vehicleList);

        for(int i = 0; i < rides; i++) {
            int a = io.getInt();
            int b = io.getInt();
            int x = io.getInt();
            int y = io.getInt();
            int s = io.getInt();
            int f = io.getInt();

            Ride r = new Ride(i, new Position(a, b), new Position(x, y), s, f);
            simulation.addRide(r);

        }
        return simulation;
    }
}
