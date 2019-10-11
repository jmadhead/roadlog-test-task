package com.roadlog.parsing;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class DecathlonCsvDoubleInputParser_NegativeTest {

  private final Reader input;
  private AbstractInputParser<Double> parser;

  public DecathlonCsvDoubleInputParser_NegativeTest(String input) {
    this.input = new StringReader(input);
  }

  @Before
  public void init() {
    parser = new DecathlonCsvDoubleInputParser();
  }

  @Parameters(name = "{0}")
  public static Object[][] testSet() {
    return new Object[][] {
      new Object[] { "John Smith;12.61;5.00;9.22;1.50;60.39;16.43;21.60;2.60;35.81" },
      new Object[] { "John Smith;12.61;5.00;9.22;1.50;60.39;16.43;21.60;2.60;" },
      new Object[] { "John Smith;12.61;5.00;9.22;1.50;60.39;16.43;21." },
      new Object[] { "John Smith;12.61;5.00;9.22;1.50;60." },
      new Object[] { "John Smith;12.61;5.00;9" },
      new Object[] { "John Smith;12" },
      new Object[] { "John" }
    };
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldThrow() throws IOException {
    parser.getResults(input);
  }

}
