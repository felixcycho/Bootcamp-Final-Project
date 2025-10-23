package com.bootcamp.demo.project_data_provider.finnhub.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.bootcamp.demo.project_data_provider.finnhub.model.dto.QuoteDTO;
import com.bootcamp.demo.project_data_provider.finnhub.service.StockService;

public class StockServiceImpl implements StockService {
  private RestTemplate restTemplate;

  public StockServiceImpl(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  @Override
  public QuoteDTO getQuote(String symbol, String apiToken) {
    // this.restTemplate.getForObject()
    // ApiUtils.xxxx
    return null;
  }


}
