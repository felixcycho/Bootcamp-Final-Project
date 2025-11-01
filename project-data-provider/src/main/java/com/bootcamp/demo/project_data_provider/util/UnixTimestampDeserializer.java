package com.bootcamp.demo.project_data_provider.util;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class UnixTimestampDeserializer extends StdDeserializer<LocalDateTime> {
  public UnixTimestampDeserializer() {
    this(null);
  }

  public UnixTimestampDeserializer(Class<?> vc) {
    super(vc);
  }

  @Override
  public LocalDateTime deserialize(JsonParser parser, DeserializationContext context) throws IOException {
    long timestamp = parser.getLongValue();
    return LocalDateTime.ofInstant(Instant.ofEpochSecond(timestamp), ZoneId.of("UTC"));
  }
}
