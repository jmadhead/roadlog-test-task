package com.roadlog.model.results;

import com.roadlog.model.activity.Activity;
import java.util.List;

public class DoubleAthleteResults implements AthleteResults<Double> {

  private final String name;
  private final List<Activity<Double>> results;

  public DoubleAthleteResults(String name, List<Activity<Double>> results) {
    this.name = name;
    this.results = results;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public List<Activity<Double>> getResults() {
    return results;
  }
}
