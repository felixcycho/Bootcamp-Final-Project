package com.bootcamp.demo.project_data_provider.contoller.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.bootcamp.demo.project_data_provider.contoller.StockDataAppOperation;
import com.bootcamp.demo.project_data_provider.model.dto.ProfileDTO;
import com.bootcamp.demo.project_data_provider.model.dto.QuoteDTO;
import com.bootcamp.demo.project_data_provider.service.StockService;


@RestController
public class StockDataAppController implements StockDataAppOperation {
  @Autowired
  private StockService stockService;

  @Override
  public QuoteDTO getCurrentQuote(String symbol, String apiToken) {
    return this.stockService.getCurrentQuote(symbol, apiToken);
  }

  @Override
  public ProfileDTO getProfile(String symbol, String apiToken) {
    return this.stockService.getProfile(symbol, apiToken);
  }

  @Override
  public List<String> fetchSymbols(String apiToken) {
    return this.stockService.fetchSymbols(apiToken);
  }

  // ! Not feasible, just for example only
  // @Override
  // public List<QuoteDTO> fetchAllSP500Quotes(List<String> symbols, String apiToken) {
  //   return this.stockService.fetchAllSP500Quotes(symbols, apiToken);
  // }

  // ! Not feasible, just for example only
  // @Override
  // public List<ProfileDTO> fetchAllSP500Profiles(List<String> symbols, String apiToken) {
  //   return this.stockService.fetchAllSP500Profiles(symbols, apiToken);
  // }

}
