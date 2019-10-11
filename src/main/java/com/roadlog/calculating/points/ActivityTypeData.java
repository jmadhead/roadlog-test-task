package com.roadlog.calculating.points;

public interface ActivityTypeData<T> {

  T getA();

  T getB();

  T getC();

  boolean isTrackEvent();

  boolean isJumpingEvent();
}
