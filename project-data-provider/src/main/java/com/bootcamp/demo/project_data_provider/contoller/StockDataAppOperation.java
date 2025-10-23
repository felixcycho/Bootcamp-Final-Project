package com.bootcamp.demo.project_data_provider.contoller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.bootcamp.demo.project_data_provider.dto.QuoteDto;

public interface StockDataAppOperation {
  @GetMapping(value = "/quote")
  QuoteDto getQuote(@RequestParam(value = "s") String symbol);
  
}
