package com.roadlog.calculating.points;

import com.roadlog.model.AthleteTotalPoints;
import com.roadlog.model.activity.Activity;
import com.roadlog.model.results.AthleteResults;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Calculator<T> {

  public List<AthleteTotalPoints> calculateTotals(List<AthleteResults<T>> parsingResults) {
    return parsingResults.stream()
        .map(this::calculateTotal)
        .collect(Collectors.toList());
  }

  protected AthleteTotalPoints calculateTotal(AthleteResults<T> athleteResults) {
    int totalPoints = athleteResults.getResults()
        .stream()
        .mapToInt(this::calculatePoints)
        .sum();
    return new AthleteTotalPoints(athleteResults.getName(), totalPoints);
  }

  int calculatePoints(Activity<T> activity) {
    ActivityTypeData<T> data = convertData(activity.getType());
    return calculatePoints(data, activity.getPerformance());
  }

  protected abstract int calculatePoints(ActivityTypeData<T> typeData, T performance);

  protected abstract ActivityTypeData<T> convertData(ActivityType typeData);

}
