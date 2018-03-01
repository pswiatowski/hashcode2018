package com.katzo.hashcode.selfdrive;

public class Vehicle {
  private int id;
  private Position position = new Position(0, 0);
  private boolean isUsed = false;

  public Vehicle(int id) {
    this.id = id;
  }

  public Journey generateJourney(Ride ride, Simulation simulation) {
    int score = 0;
    int totalTime = 0;

    if (!isUsed) {

      int travelTimeToPickUp = Util.calculatePosition(position, ride.getStartPosition());

      if (simulation.getTime() + travelTimeToPickUp <= ride.getLatestPickUpTime()) {

        score = ride.getDistance();

        if (simulation.getTime() + travelTimeToPickUp <= ride.getStartTime()) {
          score += simulation.getBonus();
        }

        totalTime = ride.getDistance() + (ride.getStartTime() - simulation.getTime());

      }
    } else {
      // TODO calculate next journey
    }

    return new Journey(ride, this, score, totalTime);
  }

  public Position getPosition() {
    return position;
  }

  public boolean isUsed() {
    return isUsed;
  }

  public void setPosition(Position position) {
    this.position = position;
  }

  public void setUsed(boolean used) {
    isUsed = used;
  }
}
