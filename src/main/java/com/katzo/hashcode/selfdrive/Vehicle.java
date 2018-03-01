package com.katzo.hashcode.selfdrive;

public class Vehicle {
  private int id;
  private Position position = new Position(0, 0);
  private boolean isUsed = false;
  private int nextStartTIme = 0 ;

  public Vehicle(int id) {
    this.id = id;
  }

  public Journey generateJourney(Ride ride, Simulation simulation, int time) {
    int score = 0;
    int totalTime = 0;

//    if (!isUsed) {
    if (time >= nextStartTIme) {

      int travelTimeToPickUp = Util.calculatePosition(position, ride.getStartPosition());

      if (time + travelTimeToPickUp <= ride.getLatestPickUpTime()) {

        score = ride.getDistance();

        if (time + travelTimeToPickUp <= ride.getStartTime()) {
          score += simulation.getBonus();
        }

        int waitTime = 0;
        if (time + travelTimeToPickUp < ride.getStartTime()) {
            waitTime = ride.getStartTime() - time - travelTimeToPickUp;
        }
        totalTime = ride.getDistance() + travelTimeToPickUp + waitTime;

      }
    } else {
      //TODO
//      int travelTimeToPickUp = Util.calculatePosition(position, ride.getStartPosition());

    }

    return new Journey(ride, this, score, totalTime);
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getNextStartTIme() {
    return nextStartTIme;
  }

  public void setNextStartTIme(int nextStartTIme) {
    this.nextStartTIme = nextStartTIme;
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

    public int getId() {
        return id;
    }
}
