package com.roadlog.parsing;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class DecathlonCsvDoubleInputParser_TimeValuesParsing_NegativeTest {

  private final String inputVal;

  private DecathlonCsvDoubleInputParser parser;

  public DecathlonCsvDoubleInputParser_TimeValuesParsing_NegativeTest(String inputVal) {
    this.inputVal = inputVal;
  }

  @Parameters(name="input \"{0}\"")
  public static Object[][] testSet() {
    return new Object[][] {
        new Object[]{""},
        new Object[]{"."},
        new Object[]{"abcd"},
        new Object[]{"1.a"},
        new Object[]{"1.1.a"}
    };
  }

  @Before
  public void init() {
    parser = new DecathlonCsvDoubleInputParser();
  }

  @Test(expected = NumberFormatException.class)
  public void test_parseSeconds() {
    parser.parseVal(inputVal);
  }

}
