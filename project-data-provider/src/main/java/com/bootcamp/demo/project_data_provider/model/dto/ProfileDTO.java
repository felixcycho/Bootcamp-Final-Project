package com.bootcamp.demo.project_data_provider.model.dto;

import java.time.LocalDateTime;
// import com.bootcamp.demo.project_data_provider.util.UnixTimestampDeserializer;
import com.bootcamp.demo.project_data_provider.util.UnixToHKTimeDeserializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
  @JsonDeserialize(using = UnixToHKTimeDeserializer.class)
  // @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
  private LocalDateTime datetime = LocalDateTime.now();

  public ProfileDTO(String symbol, String currency, String exchange,
      Long marketCapUsdMillions, String stockName, String ticker,
      String mainIndustry,
      LocalDateTime datetime) 
      {
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
