package com.katzo.hashcode.selfdrive;

import com.google.common.collect.Lists;
import com.google.common.io.Files;
import com.katzo.hashcode.HashCodeIO;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.List;

public class MainSimulation {

    public static void main(String[] args) throws IOException {
        MainSimulation mainSimulation = new MainSimulation();

        mainSimulation.solveAll();
    }

    private void solveAll() throws IOException {
        solve("2018/a_example.in", "2018/a_example.out");
        solve("2018/b_should_be_easy.in", "2018/b_should_be_easy.out");
        solve("2018/c_no_hurry.in", "2018/c_no_hurry.out");
        solve("2018/d_metropolis.in", "2018/d_metropolis.out");
        solve("2018/e_high_bonus.in", "2018/e_high_bonus.out");
    }

    private void solve(String inputFile, String outputFile) throws IOException {
        HashCodeIO io = getHashCodeIO(inputFile, outputFile);
        Simulation s = read(io);


        List<Journey> finalJourneys = Lists.newArrayList();

        for (int time = 0; time < s.getTime(); time++) {
            for (Vehicle vehicle : s.getVehicleList()) {
                List<Journey> possibleJourneys = Lists.newArrayList();

                if (s.getRides().isEmpty()) {
                    continue;
                }

                for(Ride ride : s.getRides()) {
                    Journey journey = vehicle.generateJourney(ride, s, time);
                    possibleJourneys.add(journey);
                }

                // choose the best one
                Journey bestJourney = possibleJourneys.get(0);
                if (bestJourney.getTotalTIme() != 0) {


                    double bestJourneyValue = bestJourney.calculate();
                    for (int i = 1; i < possibleJourneys.size(); i++) {
                        if (possibleJourneys.get(i).calculate() > bestJourneyValue) {
                            bestJourney = possibleJourneys.get(i);
                        }
                    }
                    finalJourneys.add(bestJourney);

                    vehicle.setPosition(bestJourney.getRide().getEndPosition());
                    vehicle.setNextStartTIme(bestJourney.getTotalTIme());

                    s.getRides().remove(bestJourney.getRide());
                }

            }
        }

        SimulationResult result = new SimulationResult(io);

        finalJourneys.stream().forEach(journey -> result.addRide(journey.getVehicle().getId() + 1, journey.getRide().getId()));
        result.print(io);
        io.close();
    }

    private Simulation read(HashCodeIO io) throws IOException {


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

    private HashCodeIO getHashCodeIO(String inputFile, String outputFile) throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource(inputFile);
        URL outResource = classLoader.getResource(outputFile);
        File file = new File(resource.getFile());
        File outFile = new File(outResource.getFile());

        return new HashCodeIO(Files.asByteSource(file).openStream(), new FileOutputStream(outFile));
    }
}
