package com.bootcamp.demo.project_data_provider.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class CompanyProfileDTO {
  private String currency;
  private String exchange;
  private Long marketCapitalization;
  @JsonProperty (value = "name")
  private String stockName;
  private String ticker;
  @JsonProperty (value = "finnhubIndustry")
  private String mainIndustry;
}
