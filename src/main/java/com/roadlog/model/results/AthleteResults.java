package com.roadlog.model.results;

import com.roadlog.model.activity.Activity;
import java.util.List;

public interface AthleteResults<T> {

  String getName();

  List<Activity<T>> getResults();

}
