package com.bootcamp.demo.project_stock_data.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class UnixTimestampDeserializer extends StdDeserializer<LocalDateTime> {

    public UnixTimestampDeserializer() {
        this(null);
    }

    public UnixTimestampDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public LocalDateTime deserialize(JsonParser parser, DeserializationContext context) throws IOException {
        String text = parser.getText(); // Handles both quoted and unquoted numbers safely
        
        long timestampMillis;
        try {
            // Parse as string first to handle large numbers and quoted values
            timestampMillis = Long.parseLong(text.trim());
        } catch (NumberFormatException e) {
            throw new IOException("Failed to parse timestamp: " + text, e);
        }

        // Most financial APIs use milliseconds (e.g., 1704067200000)
        // If you get seconds (e.g., 1704067200), treat < 10^11 as seconds
        long epochSeconds;
        if (timestampMillis < 10000000000L) { // 10-digit = seconds (up to year ~2286)
            epochSeconds = timestampMillis;
        } else { // 13-digit = milliseconds
            epochSeconds = timestampMillis / 1000;
            // Optional: preserve milliseconds for higher precision if needed
        }

        return LocalDateTime.ofInstant(
            Instant.ofEpochSecond(epochSeconds),
            ZoneId.of("UTC")
        );
    }
}