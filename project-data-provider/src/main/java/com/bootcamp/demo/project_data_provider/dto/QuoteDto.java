package com.bootcamp.demo.project_data_provider.dto;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class QuoteDto {
  private String symbol;
  private Double price;
  private Double dayHigh;
  private Double dayLow;
  private Double dayOpen;
  // private Double previousClosingPrice;
  private LocalDateTime datetime;
}
