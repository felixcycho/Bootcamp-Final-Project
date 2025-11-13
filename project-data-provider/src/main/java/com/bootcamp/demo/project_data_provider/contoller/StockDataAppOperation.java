package com.bootcamp.demo.project_data_provider.contoller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.bootcamp.demo.project_data_provider.model.dto.ProfileDTO;
import com.bootcamp.demo.project_data_provider.model.dto.QuoteDTO;
// import com.bootcamp.demo.project_data_provider.model.dto.SymbolDTO;

public interface StockDataAppOperation {
  @GetMapping(value = "/get/quote")
  // QuoteDTO getQuote(@RequestParam(value = "s") String symbol, @RequestParam String apiToken);
  QuoteDTO getQuote(@RequestParam String symbol, @RequestParam String apiToken);
  
  @GetMapping(value = "/get/profile")
  // ProfileDTO getProfile(
    // @RequestParam(value = "s") String symbol, @RequestParam String apiToken);
  ProfileDTO getProfile(@RequestParam String symbol, @RequestParam String apiToken);

}
