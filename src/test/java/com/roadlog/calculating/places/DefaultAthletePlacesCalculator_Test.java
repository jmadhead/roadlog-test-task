package com.roadlog.calculating.places;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;

import com.roadlog.model.AthletePlaces;
import com.roadlog.model.AthleteTotalPoints;
import java.util.ArrayList;
import java.util.List;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

public class DefaultAthletePlacesCalculator_Test {

  private AthletePlacesCalculator placesCalculator;

  @Before
  public void init() {
    placesCalculator = new DefaultAthletePlacesCalculator();
  }

  @Test
  public void placesAreSorted() {
    List<AthleteTotalPoints> totals = new ArrayList<>();
    totals.add(new AthleteTotalPoints("a", 1));
    totals.add(new AthleteTotalPoints("b", 5));
    totals.add(new AthleteTotalPoints("c", 100));
    totals.add(new AthleteTotalPoints("d", -1));

    String[] correctSorting = new String[]{ "c", "b", "a", "d" };

    List<AthletePlaces> places = placesCalculator.determinePlaces(totals);

    assertThat(places, hasSize(totals.size()));
    for (int i = 0; i < places.size(); i++) {
      AthletePlaces place = places.get(i);
      assertThat(place.getAthletes(), hasSize(1));
      assertThat(place.getPlaces(), hasSize(1));
      assertThat(place.getAthletes().get(0), Matchers.is(correctSorting[i]));
    }
  }

  @Test
  public void sharePlaces() {
    List<AthleteTotalPoints> totals = new ArrayList<>();
    totals.add(new AthleteTotalPoints("a", 1));
    totals.add(new AthleteTotalPoints("b", 1));
    totals.add(new AthleteTotalPoints("c", 1));
    totals.add(new AthleteTotalPoints("d", -1));

    List<AthletePlaces> places = placesCalculator.determinePlaces(totals);
    assertThat(places, hasSize(2));

    AthletePlaces firstSharedPlaces = places.get(0);
    assertThat(firstSharedPlaces.getAthletes(), hasSize(3));
    assertThat(firstSharedPlaces.getAthletes(), containsInAnyOrder("a", "b", "c"));
    assertThat(firstSharedPlaces.getPlaces(), hasSize(3));
    assertThat(firstSharedPlaces.getPlaces(), containsInAnyOrder(1, 2, 3));

    AthletePlaces lastPlace = places.get(1);
    assertThat(lastPlace.getAthletes(), hasSize(1));
    assertThat(lastPlace.getAthletes(), containsInAnyOrder("d"));
    assertThat(lastPlace.getPlaces(), hasSize(1));
    assertThat(lastPlace.getPlaces(), containsInAnyOrder(4));
  }

}
