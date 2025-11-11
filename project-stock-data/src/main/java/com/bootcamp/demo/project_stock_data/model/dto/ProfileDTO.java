package com.bootcamp.demo.project_stock_data.model.dto;

import java.time.LocalDateTime;
import com.bootcamp.demo.project_stock_data.util.UnixTimestampDeserializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProfileDTO {
  private String symbol;
  private String currency;
  private String exchange;
  @JsonProperty (value = "marketCapitalization")
  private Long marketCapUsdMillions;
  @JsonProperty (value = "name")
  private String stockName;
  private String ticker;
  @JsonProperty (value = "finnhubIndustry")
  private String mainIndustry;
  // @JsonDeserialize(using = UnixTimestampDeserializer.class)
  // @JsonProperty (value = "datetime")
  // @JsonDeserialize(using = UnixTimestampDeserializer.class)
  private LocalDateTime datetime;

  public ProfileDTO(String symbol, String currency, String exchange,
      Long marketCapUsdMillions, String stockName, String ticker,
      String mainIndustry, LocalDateTime datetime) {
        this.symbol = symbol;
        this.currency = currency;
        this.exchange = exchange;
        this.marketCapUsdMillions = marketCapUsdMillions;
        this.stockName = stockName;
        this.ticker = ticker;
        this.mainIndustry = mainIndustry;
        this.datetime = datetime;
  }

}