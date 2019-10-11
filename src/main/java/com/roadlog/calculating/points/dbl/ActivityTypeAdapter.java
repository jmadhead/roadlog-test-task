package com.roadlog.calculating.points.dbl;

import com.roadlog.calculating.points.ActivityType;
import com.roadlog.calculating.points.ActivityTypeData;

public class ActivityTypeAdapter implements ActivityTypeData<Double> {

  private final ActivityType type;

  public ActivityTypeAdapter(ActivityType type) {
    this.type = type;
  }

  public Double getA() {
    return Double.valueOf(type.getA());
  }

  public Double getB() {
    return Double.valueOf(type.getB());
  }

  public Double getC() {
    return Double.valueOf(type.getC());
  }

  public boolean isTrackEvent() {
    return type.isTrackEvent();
  }

  public boolean isJumpingEvent() {
    return type.isJumpingEvent();
  }
}
