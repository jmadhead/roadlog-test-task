  package com.roadlog.parsing;

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
  import com.roadlog.model.activity.Activity;
  import com.roadlog.model.activity.DoubleActivity;
  import com.roadlog.model.results.AthleteResults;
  import com.roadlog.model.results.DoubleAthleteResults;
  import java.io.BufferedReader;
  import java.io.IOException;
  import java.io.Reader;
  import java.util.ArrayList;
  import java.util.List;
  import java.util.regex.Pattern;
  import java.util.stream.Collectors;

  public class DecathlonCsvDoubleInputParser extends AbstractInputParser<Double> {

    private static final ActivityType[] COLUMNS_ORDER = new ActivityType[] {
        ACT_100_M,
        ACT_LONG_JUMP,
        ACT_SHOT_PUT,
        ACT_HIGH_JUMP,
        ACT_400_M,
        ACT_110_M_HURDLES,
        ACT_DISCUS_THROW,
        ACT_POLE_VAULT,
        ACT_JAVELIN_THROW,
        ACT_1500_M
    };

    private final Pattern DELIMITER = Pattern.compile(";");
    private final Pattern TIME_DELIMITER = Pattern.compile("\\.");

    @Override
    public List<AthleteResults<Double>> getResults(Reader reader) throws IOException {
      BufferedReader bReader = new BufferedReader(reader);
      List<AthleteResults<Double>> result = bReader.lines()
          .map(String::trim)
          .filter(line -> !line.isEmpty())
          .map(this::parseLine)
          .collect(Collectors.toList());
      bReader.close();
      return result;
    }

    protected AthleteResults<Double> parseLine(String line) {
      String[] parts = DELIMITER.split(line);
      if (parts.length < 11) {
        throw new IllegalArgumentException("Wrong file format. Must contain 11 or more columns.");
      }

      String athleteName = parts[0];
      List<Activity<Double>> activities = new ArrayList<>();
      for (int i = 0; i < COLUMNS_ORDER.length; i++) {
        activities.add(new DoubleActivity(COLUMNS_ORDER[i], parseVal(parts[i + 1])));
      }
      return new DoubleAthleteResults(athleteName, activities);
    }

    protected double parseVal(String val) {
      double result;
      String[] timeParts = TIME_DELIMITER.split(val);
      if (timeParts.length == 3) {
        double minutes = Double.valueOf(timeParts[0]) * 60.;
        double secondsAndMillis = Double.valueOf(timeParts[1] + "." + timeParts[2]);
        result = minutes + secondsAndMillis;
      } else {
        result = Double.valueOf(val);
      }
      return result;
    }
  }
