package com.roadlog;

import com.roadlog.calculating.places.AthletePlacesCalculator;
import com.roadlog.calculating.places.DefaultAthletePlacesCalculator;
import com.roadlog.calculating.points.dbl.DoublePointCalculator;
import com.roadlog.model.AthletePlaces;
import com.roadlog.model.AthleteTotalPoints;
import com.roadlog.model.results.AthleteResults;
import com.roadlog.output.XmlOutputFormatter;
import com.roadlog.parsing.DecathlonCsvDoubleInputParser;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.util.List;

public class DecathlonResultsCounter {

  public static void main(String[] args) throws IOException {
    InputStream input = DecathlonResultsCounter.class.getResourceAsStream("/results.csv");
    DecathlonCsvDoubleInputParser parser = new DecathlonCsvDoubleInputParser();
    List<AthleteResults<Double>> results = parser.getResults(new InputStreamReader(input));

    DoublePointCalculator calculator = new DoublePointCalculator();
    List<AthleteTotalPoints> totals = calculator.calculateTotals(results);

    AthletePlacesCalculator placesCalculator = new DefaultAthletePlacesCalculator();
    List<AthletePlaces> places = placesCalculator.determinePlaces(totals);

    XmlOutputFormatter formatter = new XmlOutputFormatter();

    StringWriter stringWriter = new StringWriter();
    formatter.write(stringWriter, places);
    System.out.println(stringWriter.toString());
  }

}
