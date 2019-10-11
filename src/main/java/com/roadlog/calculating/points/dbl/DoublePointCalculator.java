package com.roadlog.calculating.points.dbl;

import com.roadlog.calculating.points.ActivityTypeData;

public class DoublePointCalculator extends AbstractDoubleCalculator {

  private final DoubleFieldPointCalculator field;
  private final DoubleTrackPointCalculator track;

  public DoublePointCalculator() {
    this.field = new DoubleFieldPointCalculator();
    this.track = new DoubleTrackPointCalculator();
  }

  protected int calculatePoints(ActivityTypeData<Double> typeData, Double performance) {
    int result;

    double normalizedPerf = performance;
    /*if (typeData.isJumpingEvent()) {
      normalizedPerf = performance * 100.;
    } else {
      normalizedPerf = performance;
    }*/
    if (typeData.isTrackEvent()) {
      result = track.calculatePoints(typeData, normalizedPerf);
    } else {
      result = field.calculatePoints(typeData, normalizedPerf);
    }
    return result;
  }
}
