package com.roadlog.output;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.times;

import com.roadlog.model.AthletePlaces;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class XmlOutputFormatter_Test {

  private OutputFormatter formatter;;
  private List<AthletePlaces> data;

  @Before
  public void init() {
    data = new ArrayList<>();
    data.add(
        new AthletePlaces()
            .addPlace(1, "a")
            .addPlace(2, "b")
    );
    data.add(
        new AthletePlaces()
            .addPlace(3, "c")
    );

    formatter = new XmlOutputFormatter();
  }

  @Test
  public void shouldCloseOut() throws IOException {
    Writer mockedWriter = Mockito.mock(Writer.class);

    formatter.write(mockedWriter, data);
    Mockito.verify(mockedWriter, times(1)).close();
  }

  @Test
  public void compareWithReference() throws IOException {
    StringWriter out = new StringWriter();
    formatter.write(out, data);

    assertThat(out.toString(), Matchers.is(
        "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n"
            + "<results>\n"
            + "    <place name=\"a\">1-2</place>\n"
            + "    <place name=\"b\">1-2</place>\n"
            + "    <place name=\"c\">3</place>\n"
            + "</results>\n"
    ));
  }

}
