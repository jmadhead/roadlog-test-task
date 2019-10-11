package com.roadlog.output;

import com.roadlog.model.AthletePlaces;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import javax.xml.bind.JAXB;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

public class XmlOutputFormatter implements OutputFormatter {

  @Override
  public void write(Writer out, Collection<AthletePlaces> data) throws IOException {

    Results outDoc = new Results();

    for (AthletePlaces places : data) {
      String formattedPlaces = formatPlaces(places.getPlaces());
      for (String athleteName : places.getAthletes()) {
        Place xmlPlace = new Place(formattedPlaces, athleteName);
        outDoc.append(xmlPlace);
      }
    }

    JAXB.marshal(outDoc, out);
    out.flush();
    out.close();
  }

  private String formatPlaces(List<Integer> places) {
    return places.stream()
        .map(String::valueOf)
        .collect(Collectors.joining("-"));
  }

  @XmlRootElement
  private static class Results {
    private final List<Place> places;

    private Results() {
      this.places = new ArrayList<>();
    }

    public void append(Place place) {
      places.add(place);
    }

    @XmlElement(name = "place")
    public List<Place> getPlaces() {
      return places;
    }
  }

  private static class Place {
    private final String place;
    private final String name;

    private Place(String place, String name) {
      this.place = place;
      this.name = name;
    }

    @XmlValue
    public String getPlace() {
      return place;
    }

    @XmlAttribute
    public String getName() {
      return name;
    }
  }
}
