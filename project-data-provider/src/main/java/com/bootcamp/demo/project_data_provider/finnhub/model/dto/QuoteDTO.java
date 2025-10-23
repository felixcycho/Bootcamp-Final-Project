package com.bootcamp.demo.project_data_provider.finnhub.model.dto;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class QuoteDTO {
  private String symbol;
  private Double price; 
  private Double dayHigh;
  private Double dayLow;
  private Double dayOpen;
  private LocalDateTime datetime = LocalDateTime.now();
}
