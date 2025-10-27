package com.bootcamp.demo.project_data_provider.model.dto;

import java.time.LocalDateTime;
import com.bootcamp.demo.project_data_provider.config.UnixTimestampDeserializer;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
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
  @JsonDeserialize(using = UnixTimestampDeserializer.class)
  private LocalDateTime datetime = LocalDateTime.now();
}
