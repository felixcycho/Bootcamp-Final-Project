package com.bootcamp.demo.project_data_provider.contoller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.bootcamp.demo.project_data_provider.model.dto.CompanyProfileDTO;
import com.bootcamp.demo.project_data_provider.model.dto.QuoteDTO;

public interface StockDataAppOperation {
  @GetMapping(value = "/quote")
  QuoteDTO getQuote(@RequestParam(value = "s") String symbol, @RequestParam String apiToken);
  
  @GetMapping(value = "/profile2")
  CompanyProfileDTO getCompanyProfile(@RequestParam(value = "s") String symbol, @RequestParam String apiToken);
}
