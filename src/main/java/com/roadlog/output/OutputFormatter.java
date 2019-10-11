package com.roadlog.output;

import com.roadlog.model.AthletePlaces;
import java.io.IOException;
import java.io.Writer;
import java.util.Collection;

public interface OutputFormatter {

  void write(Writer out, Collection<AthletePlaces> data) throws IOException;

}
