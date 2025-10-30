package com.bootcamp.demo.project_data_provider.contoller;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.bootcamp.demo.project_data_provider.model.dto.ProfileDTO;
import com.bootcamp.demo.project_data_provider.model.dto.QuoteDTO;

public interface StockDataAppOperation {
  @GetMapping(value = "/get/current_quote")
  // QuoteDTO getQuote(@RequestParam(value = "s") String symbol, @RequestParam String apiToken);
  QuoteDTO getCurrentQuote(@RequestParam String symbol, @RequestParam String apiToken);
  

  @GetMapping(value = "/get/profile")
  // CompanyProfileDTO getCompanyProfile(
    // @RequestParam(value = "s") String symbol, @RequestParam String apiToken);
  ProfileDTO getProfile(
    @RequestParam String symbol, @RequestParam String apiToken);

 


  @GetMapping(value = "/fetch/symbols")
  List<String> fetchSymbols(
    @RequestParam String apiToken);
  



  // ! Not feasible, just for example only
  // @GetMapping(value = "/fetch/quotes")
  // List<QuoteDTO> fetchAllSP500Quotes(
  //   @RequestParam List<String> symbols, @RequestParam String apiToken);


  // ! Not feasible, just for example only
  // @GetMapping(value = "/fetch/profiles")
  // List<ProfileDTO> fetchAllSP500Profiles(
  //   @RequestParam List<String> symbols, @RequestParam String apiToken);
}
