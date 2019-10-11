package com.roadlog.calculating.points;

public enum ActivityType implements ActivityTypeData<String> {

  ACT_100_M        ("25.4347", "18",   "1.81"),
  ACT_LONG_JUMP    ("0.14354", "220",  "1.40"),
  ACT_SHOT_PUT     ("51.39",   "1.5",  "1.05"),
  ACT_HIGH_JUMP    ("0.8465",  "75",   "1.42"),
  ACT_400_M        ("1.53775", "82",   "1.81"),
  ACT_110_M_HURDLES("5.74352", "28.5", "1.92"),
  ACT_DISCUS_THROW ("12.91",   "4",    "1.10"),
  ACT_POLE_VAULT   ("0.2797",  "100",  "1.35"),
  ACT_JAVELIN_THROW("10.14",   "7",    "1.08"),
  ACT_1500_M       ("0.03768", "480",  "1.85");

  private final String a;
  private final String b;
  private final String c;

  ActivityType(String a, String b, String c) {
    this.a = a;
    this.b = b;
    this.c = c;
  }

  public String getA() {
    return a;
  }

  public String getB() {
    return b;
  }

  public String getC() {
    return c;
  }

  public boolean isTrackEvent() {
    return this == ACT_100_M
      || this == ACT_400_M
      || this == ACT_110_M_HURDLES
      || this == ACT_1500_M;
  }

  public boolean isJumpingEvent() {
    return this == ACT_LONG_JUMP
        || this == ACT_HIGH_JUMP
        || this == ACT_POLE_VAULT;
  }

}
