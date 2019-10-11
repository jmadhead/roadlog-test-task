package com.roadlog.parsing;

import com.roadlog.model.results.AthleteResults;
import java.io.IOException;
import java.io.Reader;
import java.util.List;

public abstract class AbstractInputParser<T> {

  public abstract List<AthleteResults<T>> getResults(Reader reader) throws IOException;

}
