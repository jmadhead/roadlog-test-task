package com.roadlog.calculating.places;

import com.roadlog.model.AthletePlaces;
import com.roadlog.model.AthleteTotalPoints;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DefaultAthletePlacesCalculator implements AthletePlacesCalculator {

  @Override
  public List<AthletePlaces> determinePlaces(List<AthleteTotalPoints> totals) {
    List<AthleteTotalPoints> sortedTotals = totals.stream().sorted().collect(Collectors.toList());
    AthletePlaces currentPlace = new AthletePlaces();
    List<AthletePlaces> places = new ArrayList<>();
    places.add(currentPlace);
    int previousAthletePoints = -1;
    int currentPlaceNumber = 1;
    for (AthleteTotalPoints total : sortedTotals) {
      if (previousAthletePoints != total.getPoints() && previousAthletePoints != -1) {
        currentPlace = new AthletePlaces();
        places.add(currentPlace);
      }
      currentPlace.addPlace(currentPlaceNumber, total.getName());
      previousAthletePoints = total.getPoints();
      currentPlaceNumber++;
    }

    return places;
  }
}
