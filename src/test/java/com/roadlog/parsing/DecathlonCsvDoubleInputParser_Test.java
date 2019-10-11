package com.roadlog.parsing;

import static org.hamcrest.CoreMatchers.any;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;

import com.roadlog.calculating.points.ActivityType;
import com.roadlog.model.activity.Activity;
import com.roadlog.model.results.AthleteResults;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.List;
import java.util.Optional;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

public class DecathlonCsvDoubleInputParser_Test {

  public static final String CORRECT_INPUT = "John Smith;12.61;5.00;9.22;1.50;60.39;16.43;21.60;2.60;35.81;5.25.72";
  private AbstractInputParser<Double> parser;

  @Before
  public void init() {
    parser = new DecathlonCsvDoubleInputParser();
  }

  @Test
  public void shouldCloseInput() throws IOException {
    Reader mockedIn = Mockito.mock(Reader.class);
    Mockito.when(mockedIn.read(ArgumentMatchers.any(char[].class), anyInt(), anyInt())).thenReturn(-1);
    parser.getResults(mockedIn);

    Mockito.verify(mockedIn, times(1)).close();
  }

  @Test
  public void shouldSkipEmptyRows() throws IOException {
    StringReader in = new StringReader(CORRECT_INPUT + "\n" + "\n" + CORRECT_INPUT);
    List<AthleteResults<Double>> results = parser.getResults(in);
    assertThat(results, hasSize(2));
  }

  @Test
  public void positive() throws IOException {
    StringReader in = new StringReader(CORRECT_INPUT);
    List<AthleteResults<Double>> results = parser.getResults(in);

    assertThat(results, is(notNullValue()));
    assertThat(results, Matchers.hasSize(1));

    AthleteResults<Double> athleteResults = results.get(0);
    assertThat(athleteResults.getName(), is("John Smith"));

    containsActivityWithValue(athleteResults, ActivityType.ACT_100_M, 12.61);
    containsActivityWithValue(athleteResults, ActivityType.ACT_LONG_JUMP, 5.00);
    containsActivityWithValue(athleteResults, ActivityType.ACT_SHOT_PUT, 9.22);
    containsActivityWithValue(athleteResults, ActivityType.ACT_HIGH_JUMP, 1.50);
    containsActivityWithValue(athleteResults, ActivityType.ACT_400_M, 60.39);
    containsActivityWithValue(athleteResults, ActivityType.ACT_110_M_HURDLES, 16.43);
    containsActivityWithValue(athleteResults, ActivityType.ACT_DISCUS_THROW, 21.60);
    containsActivityWithValue(athleteResults, ActivityType.ACT_POLE_VAULT, 2.60);
    containsActivityWithValue(athleteResults, ActivityType.ACT_JAVELIN_THROW, 35.81);
    containsActivityWithValue(athleteResults, ActivityType.ACT_1500_M, 325.72);
  }

  private void containsActivityWithValue(AthleteResults<Double> results, ActivityType type, double value) {
    assertThat(results.getResults(), hasItems(hasProperty("type", is(type))));

    Optional<Activity<Double>> ofType = results.getResults()
        .stream()
        .filter(act -> act.getType() == type)
        .findFirst();

    assertThat("Performance for " + type, ofType.get().getPerformance(), is(value));
  }

}
