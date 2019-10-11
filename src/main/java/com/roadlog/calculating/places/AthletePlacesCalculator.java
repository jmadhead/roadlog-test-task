package com.roadlog.calculating.places;

import com.roadlog.model.AthletePlaces;
import com.roadlog.model.AthleteTotalPoints;
import java.util.List;

public interface AthletePlacesCalculator {

  List<AthletePlaces> determinePlaces(List<AthleteTotalPoints> totals);

}
