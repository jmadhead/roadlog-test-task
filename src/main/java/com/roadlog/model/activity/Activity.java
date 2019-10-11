package com.roadlog.model.activity;

import com.roadlog.calculating.points.ActivityType;

public interface Activity<T> {

  T getPerformance();

  ActivityType getType();
}
