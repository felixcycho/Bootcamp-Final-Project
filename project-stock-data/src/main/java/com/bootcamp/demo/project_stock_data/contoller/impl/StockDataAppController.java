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
  public QuoteDTO getQuote(String symbol, String apiToken) {
    return this.stockService.getQuote(symbol, apiToken);
  }

  @Override
  public ProfileDTO getProfile(String symbol, String apiToken) {
    return this.stockService.getProfile(symbol, apiToken);  
  }

  // @Override
  // public QuoteDTO getQuote(String symbol) {
  //   return this.stockService.getQuote(symbol);
  // }

  // @Override
  // public ProfileDTO getProfile(String symbol) {
  //   return this.stockService.getProfile(symbol);  
  // }


  @Override
  public List<SymbolDTO> fetchSymbols() {
    return this.stockService.fetchSymbols();
  }

}
