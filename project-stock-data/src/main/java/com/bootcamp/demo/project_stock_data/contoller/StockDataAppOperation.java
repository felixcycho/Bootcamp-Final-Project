package com.bootcamp.demo.project_stock_data.contoller;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.bootcamp.demo.project_stock_data.model.dto.ProfileDTO;
import com.bootcamp.demo.project_stock_data.model.dto.QuoteDTO;
import com.bootcamp.demo.project_stock_data.model.dto.SymbolDTO;

public interface StockDataAppOperation {
  @GetMapping(value = "/fetch/symbols")
  List<SymbolDTO> fetchSymbols();

  @GetMapping(value = "/get/quote")
  QuoteDTO getQuote(@RequestParam String symbol, @RequestParam String apiToken);
  
  @GetMapping(value = "/get/profile")
  ProfileDTO getProfile(@RequestParam String symbol, @RequestParam String apiToken);

  // ! Not feasible, just for example only
  // @GetMapping(value = "/fetch/quotes")
  // List<QuoteDTO> fetchAllSP500Quotes(
  //   @RequestParam List<String> symbols, @RequestParam String apiToken);


  // ! Not feasible, just for example only
  // @GetMapping(value = "/fetch/profiles")
  // List<ProfileDTO> fetchAllSP500Profiles(
  //   @RequestParam List<String> symbols, @RequestParam String apiToken);
  
}
