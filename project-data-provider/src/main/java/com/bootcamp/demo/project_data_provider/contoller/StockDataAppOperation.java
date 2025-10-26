package com.bootcamp.demo.project_data_provider.contoller;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.bootcamp.demo.project_data_provider.model.dto.CompanyProfileDTO;
import com.bootcamp.demo.project_data_provider.model.dto.QuoteDTO;

public interface StockDataAppOperation {
  @GetMapping(value = "/get/quote")
  // QuoteDTO getQuote(@RequestParam(value = "s") String symbol, @RequestParam String apiToken);
  QuoteDTO getQuote(@RequestParam String symbol, @RequestParam String apiToken);
  

  @GetMapping(value = "/get/profile")
  // CompanyProfileDTO getCompanyProfile(
    // @RequestParam(value = "s") String symbol, @RequestParam String apiToken);
  CompanyProfileDTO getCompanyProfile(
    @RequestParam String symbol, @RequestParam String apiToken);


  @GetMapping(value = "/fetch/symbols")
  List<String> fetchSymbols(
    @RequestParam String apiToken);


  @GetMapping(value = "/fetch/quotes")
  List<QuoteDTO> fetchAllSP500Quotes(
    @RequestParam List<String> symbols, @RequestParam String apiToken);


  @GetMapping(value = "/fetch/profiles")
  List<CompanyProfileDTO> fetchAllSP500CompanyProfiles(
    @RequestParam List<String> symbols, @RequestParam String apiToken);
}
