package com.bootcamp.demo.project_data_provider.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CompanyProfileDto {
  private String symbol;
  private String currency;
  private String exchange;
  private Long marketCapitalization;
  private String name;
  private String ticker;
  private String industry;
}
