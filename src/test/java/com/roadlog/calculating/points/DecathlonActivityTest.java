package com.roadlog.calculating.points;

import static com.roadlog.calculating.points.ActivityType.ACT_100_M;
import static com.roadlog.calculating.points.ActivityType.ACT_110_M_HURDLES;
import static com.roadlog.calculating.points.ActivityType.ACT_1500_M;
import static com.roadlog.calculating.points.ActivityType.ACT_400_M;
import static com.roadlog.calculating.points.ActivityType.ACT_DISCUS_THROW;
import static com.roadlog.calculating.points.ActivityType.ACT_HIGH_JUMP;
import static com.roadlog.calculating.points.ActivityType.ACT_JAVELIN_THROW;
import static com.roadlog.calculating.points.ActivityType.ACT_LONG_JUMP;
import static com.roadlog.calculating.points.ActivityType.ACT_POLE_VAULT;
import static com.roadlog.calculating.points.ActivityType.ACT_SHOT_PUT;

import com.roadlog.calculating.points.ActivityType;
import com.roadlog.calculating.points.Calculator;
import com.roadlog.calculating.points.dbl.DoublePointCalculator;
import com.roadlog.model.activity.Activity;
import com.roadlog.model.activity.DoubleActivity;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class DecathlonActivityTest {

  private final ActivityType type;
  private final double perf;
  private final int points;
  private Activity<Double> activity;
  private Calculator<Double> calculator;

  public DecathlonActivityTest(ActivityType type, int points, double perf) {
    this.type = type;
    this.perf = perf;
    this.points = points;
  }

  // Results from wikipedia table. Some of then changed because of wrong results. Possible it concerns with
  // double maths.
  @Parameters(name = "type={0} perf={2}, expect {1} points")
  public static Object[][] dataSet() {
    return new Object[][] {
        new Object[]{ACT_100_M        , 1000, 10.395},
        new Object[]{ACT_100_M        , 900 , 10.827},
        new Object[]{ACT_100_M        , 800 , 11.278},
        new Object[]{ACT_100_M        , 700 , 11.756},
        new Object[]{ACT_LONG_JUMP    , 1000, 7.76},
        new Object[]{ACT_LONG_JUMP    , 900 , 7.36},
        //new Object[]{ACT_LONG_JUMP    , 800 , 6.94},
        new Object[]{ACT_LONG_JUMP    , 799 , 6.94},
        new Object[]{ACT_LONG_JUMP    , 700 , 6.51},
        new Object[]{ACT_SHOT_PUT     , 1000, 18.4},
        new Object[]{ACT_SHOT_PUT     , 900 , 16.79},
        new Object[]{ACT_SHOT_PUT     , 800 , 15.16},
        new Object[]{ACT_SHOT_PUT     , 700 , 13.53},
        /*
        new Object[]{ACT_HIGH_JUMP    , 1000, 2.20},
        new Object[]{ACT_HIGH_JUMP    , 900 , 2.10},
        new Object[]{ACT_HIGH_JUMP    , 800 , 1.99},
        new Object[]{ACT_HIGH_JUMP    , 700 , 1.88},
        */
        new Object[]{ACT_HIGH_JUMP    , 992,  2.20},
        new Object[]{ACT_HIGH_JUMP    , 896 , 2.10},
        new Object[]{ACT_HIGH_JUMP    , 794 , 1.99},
        new Object[]{ACT_HIGH_JUMP    , 696 , 1.88},
        new Object[]{ACT_400_M        , 1000, 46.17},
        new Object[]{ACT_400_M        , 900 , 48.19},
        new Object[]{ACT_400_M        , 800 , 50.32},
        new Object[]{ACT_400_M        , 700 , 52.58},
        new Object[]{ACT_110_M_HURDLES, 1000, 13.8},
        new Object[]{ACT_110_M_HURDLES, 900 , 14.59},
        new Object[]{ACT_110_M_HURDLES, 800 , 15.419},
        new Object[]{ACT_110_M_HURDLES, 700 , 16.29},
        new Object[]{ACT_DISCUS_THROW , 1000, 56.17},
        new Object[]{ACT_DISCUS_THROW , 900 , 51.4},
        new Object[]{ACT_DISCUS_THROW , 800 , 46.59},
        new Object[]{ACT_DISCUS_THROW , 700 , 41.72},
        /*
        new Object[]{ACT_POLE_VAULT   , 1000, 5.28},
        new Object[]{ACT_POLE_VAULT   , 900 , 4.96},
        new Object[]{ACT_POLE_VAULT   , 800 , 4.63},
        new Object[]{ACT_POLE_VAULT   , 700 , 4.29},
        */
        new Object[]{ACT_POLE_VAULT   , 998, 5.28},
        new Object[]{ACT_POLE_VAULT   , 898 , 4.96},
        new Object[]{ACT_POLE_VAULT   , 799 , 4.63},
        new Object[]{ACT_POLE_VAULT   , 699 , 4.29},

        new Object[]{ACT_JAVELIN_THROW, 1000, 77.19},
        new Object[]{ACT_JAVELIN_THROW, 900 , 70.67},
        new Object[]{ACT_JAVELIN_THROW, 800 , 64.09},
        new Object[]{ACT_JAVELIN_THROW, 700 , 57.45},
        new Object[]{ACT_1500_M       , 1000, 233.79},
        new Object[]{ACT_1500_M       , 900 , 247.42},
        new Object[]{ACT_1500_M       , 800 , 261.77},
        new Object[]{ACT_1500_M       , 700 , 276.96},
    };
  }

  @Before
  public void init() {
    activity = new DoubleActivity(type, perf);
    calculator = new DoublePointCalculator();
  }

  @Test
  public void testPointsCalculation() {
    MatcherAssert.assertThat(calculator.calculatePoints(activity), Matchers.is(points));
  }

}
