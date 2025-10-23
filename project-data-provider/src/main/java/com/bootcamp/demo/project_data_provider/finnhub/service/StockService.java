package com.bootcamp.demo.project_data_provider.finnhub.service;

import com.bootcamp.demo.project_data_provider.finnhub.model.dto.QuoteDTO;

public interface StockService {
  // real-time quote
  QuoteDTO getQuote(String symbol, String apiToken);
}
