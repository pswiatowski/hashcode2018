package com.katzo.hashcode.selfdrive;

import com.google.common.io.Files;
import com.katzo.hashcode.HashCodeIO;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class MainSimulation {

    public static void main(String[] args) throws IOException {
        MainSimulation mainSimulation = new MainSimulation();

        Simulation s = mainSimulation.read("2018/a_example.in");

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


        Simulation simulation = new Simulation(rows, cols, vehicles, rides, bonus, T);

        for(int i = 0; i < rides; i++) {
            int a = io.getInt();
            int b = io.getInt();
            int x = io.getInt();
            int y = io.getInt();
            int s = io.getInt();
            int f = io.getInt();

            Ride r = new Ride(new Position(a, b), new Position(x, y), s, f);
            simulation.addRide(r);

        }
        return simulation;
    }
}
