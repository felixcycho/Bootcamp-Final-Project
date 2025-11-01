package com.bootcamp.demo.project_data_provider.model.dto;

import java.time.LocalDateTime;
import com.bootcamp.demo.project_data_provider.finnhub.util.UnixTimestampDeserializer;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfileDTO {
  private String symbol;
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

  public ProfileDTO(String symbol, String currency, String exchange,
      Long marketCapitalization, String stockName, String ticker,
      String mainIndustry, LocalDateTime datetime) {
        this.symbol = symbol;
        this.currency = currency;
        this.exchange = exchange;
        this.marketCapitalization = marketCapitalization;
        this.stockName = stockName;
        this.ticker = ticker;
        this.mainIndustry = mainIndustry;
        this.datetime = datetime;
  }
}
