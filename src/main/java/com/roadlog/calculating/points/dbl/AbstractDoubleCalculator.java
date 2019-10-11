package com.roadlog.calculating.points.dbl;

import com.roadlog.calculating.points.ActivityType;
import com.roadlog.calculating.points.ActivityTypeData;
import com.roadlog.calculating.points.Calculator;

public abstract class AbstractDoubleCalculator extends Calculator<Double> {

  @Override
  protected ActivityTypeData<Double> convertData(ActivityType typeData) {
    return new ActivityTypeAdapter(typeData);
  }

}
