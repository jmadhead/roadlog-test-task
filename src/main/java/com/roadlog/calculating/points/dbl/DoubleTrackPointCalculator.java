package com.roadlog.calculating.points.dbl;

import com.roadlog.calculating.points.ActivityTypeData;

class DoubleTrackPointCalculator extends AbstractDoubleCalculator {

  protected int calculatePoints(ActivityTypeData<Double> type, Double performance) {
    double powBase = type.getB() - performance;
    double powResult = Math.pow(powBase, type.getC());
    return (int) (type.getA() * powResult);
  }
}
