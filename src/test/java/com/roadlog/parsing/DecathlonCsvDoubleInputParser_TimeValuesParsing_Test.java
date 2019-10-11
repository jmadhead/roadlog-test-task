package com.roadlog.parsing;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class DecathlonCsvDoubleInputParser_TimeValuesParsing_Test {

  private final String inputVal;
  private final double expectedResult;

  private DecathlonCsvDoubleInputParser parser;

  public DecathlonCsvDoubleInputParser_TimeValuesParsing_Test(String inputVal, double expectedResult) {
    this.inputVal = inputVal;
    this.expectedResult = expectedResult;
  }

  @Parameters(name="input \"{0}\" expected \"{1}\"")
  public static Object[][] testSet() {
    return new Object[][] {
      new Object[]{"0", 0.0},
      new Object[]{"0.1", 0.1},
      new Object[]{".1", 0.1},
      new Object[]{"1.", 1.},
      new Object[]{"1", 1},
      new Object[]{"1.2", 1.2},
      new Object[]{"1.1.1", 61.1}
    };
  }

  @Before
  public void init() {
    parser = new DecathlonCsvDoubleInputParser();
  }

  @Test
  public void test_parseSeconds() {
    double res = parser.parseVal(inputVal);
    assertThat(res, is(expectedResult));
  }
}
