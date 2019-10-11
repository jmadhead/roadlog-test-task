package com.roadlog.calculating.points.dbl;

import com.roadlog.calculating.points.ActivityTypeData;

class DoubleFieldPointCalculator extends AbstractDoubleCalculator {

  protected int calculatePoints(ActivityTypeData<Double> type, Double performance) {
    double powBase;
    double powResult;
    if (type.isJumpingEvent()) {
      powBase = (performance * 100. - type.getB());
    } else {
      powBase = performance - type.getB();
    }
    powResult = Math.pow(powBase, type.getC());
    return ((int) (type.getA() * powResult));
  }
}
