package com.roadlog.model;

import java.util.ArrayList;
import java.util.List;

public class AthletePlaces {

  private List<Integer> places;
  private List<String> athletes;

  public AthletePlaces() {
    this.places = new ArrayList<>();
    this.athletes = new ArrayList<>();
  }

  public AthletePlaces addPlace(int place, String name) {
    places.add(place);
    athletes.add(name);
    return this;
  }

  public List<Integer> getPlaces() {
    return places;
  }

  public List<String> getAthletes() {
    return athletes;
  }
}
