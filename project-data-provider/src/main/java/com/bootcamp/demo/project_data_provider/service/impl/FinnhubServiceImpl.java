package com.bootcamp.demo.project_data_provider.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.bootcamp.demo.project_data_provider.finnhub.model.dto.QuoteDTO;
import com.bootcamp.demo.project_data_provider.finnhub.service.StockService;
import com.bootcamp.demo.project_data_provider.service.FinnhubService;


@Service
public class FinnhubServiceImpl implements FinnhubService {

  @Autowired
  private StockService stockService;

  @Value("${api-service.finnhub.api-token}")  // TBC
  private String apiToken;

  @Override
  public QuoteDTO getQuote(String symbol) {
    return stockService.getQuote(symbol, this.apiToken);
  }


}
