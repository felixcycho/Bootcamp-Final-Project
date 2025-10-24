package com.bootcamp.demo.project_data_provider.contoller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.bootcamp.demo.project_data_provider.contoller.StockDataAppOperation;
import com.bootcamp.demo.project_data_provider.model.dto.CompanyProfileDTO;
import com.bootcamp.demo.project_data_provider.model.dto.QuoteDTO;
import com.bootcamp.demo.project_data_provider.service.StockService;


@RestController
public class StockDataAppController implements StockDataAppOperation {
  @Autowired
  private StockService stockService;

  @Override
  public QuoteDTO getQuote(String symbol, String apiToken) {
    return this.stockService.getQuote(symbol, apiToken);
  }

  @Override
  public CompanyProfileDTO getCompanyProfile(String symbol, String apiToken) {
    return this.stockService.getCompanyProfile(symbol, apiToken);
  }
}
