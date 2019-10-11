package com.roadlog.model.activity;

import com.roadlog.calculating.points.ActivityType;

public class DoubleActivity implements Activity<Double> {

  private final ActivityType type;
  private final double performance;

  public DoubleActivity(ActivityType type, double performance) {
    this.performance = performance;
    this.type = type;
  }

  public Double getPerformance() {
    return performance;
  }

  public ActivityType getType() {
    return type;
  }
}
