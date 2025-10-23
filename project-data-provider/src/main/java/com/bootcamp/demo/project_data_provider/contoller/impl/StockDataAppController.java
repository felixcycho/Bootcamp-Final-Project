package com.bootcamp.demo.project_data_provider.contoller.impl;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.bootcamp.demo.project_data_provider.contoller.StockDataAppOperation;
import com.bootcamp.demo.project_data_provider.dto.QuoteDto;

@RestController
public class StockDataAppController implements StockDataAppOperation {
  @Override
  public QuoteDto getQuote(String symbol) {
    // placeholder
    return QuoteDto.builder() //
        .symbol("TSLA") //
        .price(23.4) //
        .dayHigh(26.5) //
        .dayLow(23.1) //
        .dayOpen(24.0) //
        .build();
  }
}
