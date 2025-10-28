package com.bootcamp.demo.project_data_provider.model.dto;

import java.time.LocalDateTime;
import com.bootcamp.demo.project_data_provider.finnhub.util.UnixTimestampDeserializer;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;

@Getter
public class QuoteDTO {

  private String symbol;
  @JsonProperty (value = "c")
  private Double price;
  @JsonProperty (value = "h")
  private Double dayHigh;
  @JsonProperty (value = "l")
  private Double dayLow;
  @JsonProperty (value = "o")
  private Double dayOpen;
  @JsonProperty (value = "pc")
  private Double previousClosingPrice;
  @JsonProperty (value = "t")
  @JsonDeserialize(using = UnixTimestampDeserializer.class)
  private LocalDateTime datetime = LocalDateTime.now();

  // Constructor for manual creation
    public QuoteDTO(String symbol, Double price, Double dayHigh, 
      Double dayLow, Double dayOpen, Double previousClosingPrice, 
      LocalDateTime datetime) {
        this.symbol = symbol;
        this.price = price;
        this.dayHigh = dayHigh;
        this.dayLow = dayLow;
        this.dayOpen = dayOpen;
        this.previousClosingPrice = previousClosingPrice;
        this.datetime = datetime;
    }
}
