package com.bootcamp.demo.project_data_provider.model.dto;

import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonProperty;
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
  @JsonProperty (value = "t")
  private LocalDateTime datetime = LocalDateTime.now();
}
