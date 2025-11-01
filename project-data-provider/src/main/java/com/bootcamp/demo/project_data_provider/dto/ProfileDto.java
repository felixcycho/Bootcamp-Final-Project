package com.bootcamp.demo.project_data_provider.dto;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProfileDto {
  private String symbol;
  private String currency;
  private String exchange;
  private Long marketCapitalization;
  private String stockName;
  private String ticker;
  private String mainIndustry;
  private LocalDateTime datetime;
}
