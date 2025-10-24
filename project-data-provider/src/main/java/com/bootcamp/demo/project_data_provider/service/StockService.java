package com.bootcamp.demo.project_data_provider.service;

import com.bootcamp.demo.project_data_provider.model.dto.CompanyProfileDTO;
import com.bootcamp.demo.project_data_provider.model.dto.QuoteDTO;

public interface StockService {
   QuoteDTO getQuote(String symbol, String apiToken);
   CompanyProfileDTO getCompanyProfile(String symbol, String apiToken);
}
