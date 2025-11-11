package com.bootcamp.demo.project_stock_data.model.dto;

import java.time.LocalDateTime;
import com.bootcamp.demo.project_stock_data.util.UnixTimestampDeserializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class QuoteDTO {

  private String symbol;
  @JsonProperty (value = "c")
  private Double price;
  @JsonProperty (value = "d")
  private Double priceChange;
  @JsonProperty (value = "dp")
  private Double percentChange;
  @JsonProperty (value = "h")
  private Double dayHigh;
  @JsonProperty (value = "l")
  private Double dayLow;
  @JsonProperty (value = "o")
  private Double dayOpen;
  @JsonProperty (value = "pc")
  private Double previousClosingPrice;
  // @JsonProperty (value = "t")
  // @JsonDeserialize(using = UnixTimestampDeserializer.class)
  // @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
  private LocalDateTime closingTime;
  // @JsonDeserialize(using = UnixTimestampDeserializer.class)
  // @JsonProperty (value = "currentTime")
  // @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
  private LocalDateTime currentTime;

  public QuoteDTO(String symbol, Double price, Double priceChange, Double percentChange, 
    Double dayHigh, Double dayLow, Double dayOpen, 
    Double previousClosingPrice, 
    LocalDateTime closingTime, LocalDateTime currentTime) {
      this.symbol = symbol;
      this.price = price;
      this.priceChange = priceChange;
      this.percentChange = percentChange;
      this.dayHigh = dayHigh;
      this.dayLow = dayLow;
      this.dayOpen = dayOpen;
      this.previousClosingPrice = previousClosingPrice;
      this.closingTime = closingTime;
      this.currentTime = currentTime;
  }
}
