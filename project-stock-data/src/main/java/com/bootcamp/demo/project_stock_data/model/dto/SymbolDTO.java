package com.bootcamp.demo.project_stock_data.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;


/**
 * Data Transfer Object for one line of sp500_symbols.txt
 *
 * Example line:  AAPL Apple Inc.
 */
@Getter
@Setter
public class SymbolDTO{
  @JsonProperty("symbol")   
  private String symbol;      // e.g. "AAPL"
  @JsonProperty("name")     
  private String companyName; // e.g. "Apple Inc."

  public SymbolDTO(String symbol, String companyName) {
    this.symbol = symbol;
    this.companyName = companyName;
  }
 
}
