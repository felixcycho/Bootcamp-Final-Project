package com.bootcamp.demo.project_data_provider.contoller;

import org.springframework.web.bind.annotation.GetMapping;
import com.bootcamp.demo.project_data_provider.model.dto.QuoteDto;
import org.springframework.web.bind.annotation.RequestParam;


public interface StockDataAppOperation {
  @GetMapping(value = "/quote")
  QuoteDto getQuote(@RequestParam(value = "s") String symbol);
  
}
