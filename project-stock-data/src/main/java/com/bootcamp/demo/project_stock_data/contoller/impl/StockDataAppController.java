package com.bootcamp.demo.project_stock_data.contoller.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.bootcamp.demo.project_stock_data.contoller.StockDataAppOperation;
import com.bootcamp.demo.project_stock_data.model.dto.ProfileDTO;
import com.bootcamp.demo.project_stock_data.model.dto.QuoteDTO;
import com.bootcamp.demo.project_stock_data.model.dto.SymbolDTO;
import com.bootcamp.demo.project_stock_data.service.StockService;


@RestController
public class StockDataAppController implements StockDataAppOperation {
  @Autowired
  private StockService stockService;
  
  @Override
  public List<SymbolDTO> fetchSymbols() {
    return this.stockService.fetchSymbols();
  }
  
  @Override
  public QuoteDTO getQuote(String symbol, String apiToken) {
    return this.stockService.getQuote(symbol, apiToken);
  }

  @Override
  public ProfileDTO getProfile(String symbol, String apiToken) {
    return this.stockService.getProfile(symbol, apiToken);  
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
