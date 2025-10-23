package com.bootcamp.demo.project_data_provider.service;

import com.bootcamp.demo.project_data_provider.finnhub.model.dto.QuoteDTO;

public interface FinnhubService {
   QuoteDTO getQuote(String symbol);
}
