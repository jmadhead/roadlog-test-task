package com.roadlog.model;

public class AthleteTotalPoints implements Comparable<AthleteTotalPoints> {

  private final String name;
  private final int points;

  public AthleteTotalPoints(String name, int points) {
    this.name = name;
    this.points = points;
  }

  public String getName() {
    return name;
  }

  public int getPoints() {
    return points;
  }

  @Override
  public int compareTo(AthleteTotalPoints other) {
    return other.points - points;
  }
}
