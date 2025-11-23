package com.bootcamp.demo.project_stock_data.contoller.impl;

import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.bootcamp.demo.project_stock_data.contoller.StockDataAppOperation;
import com.bootcamp.demo.project_stock_data.model.dto.InfoDTO;
import com.bootcamp.demo.project_stock_data.model.dto.OhlcDTO;
import com.bootcamp.demo.project_stock_data.model.dto.ProfileDTO;
import com.bootcamp.demo.project_stock_data.model.dto.QuoteDTO;
import com.bootcamp.demo.project_stock_data.model.dto.SymbolDTO;
import com.bootcamp.demo.project_stock_data.service.StockService;
import io.micrometer.common.lang.Nullable;


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
  public ProfileDTO getProfile(String symbol) {
    return this.stockService.getProfile(symbol);  
  }

  @Override
  public InfoDTO getInfo(String symbol) {
    return this.stockService.getInfo(symbol);
  }

  @Override
  public List<OhlcDTO> getOhlcs(
    String symbol, 
    @Nullable LocalDate startDate, 
    @Nullable LocalDate endDate) {
    return this.stockService.getOhlcs(symbol, startDate, endDate);
  }


}
