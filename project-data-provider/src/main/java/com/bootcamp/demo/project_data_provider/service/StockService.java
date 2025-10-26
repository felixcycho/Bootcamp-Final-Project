package com.bootcamp.demo.project_data_provider.service;

import java.util.List;
import com.bootcamp.demo.project_data_provider.model.dto.CompanyProfileDTO;
import com.bootcamp.demo.project_data_provider.model.dto.QuoteDTO;

public interface StockService {

   QuoteDTO getQuote(String symbol, String apiToken);

   CompanyProfileDTO getCompanyProfile(String symbol, String apiToken);

   List<String> fetchSymbols(String apiToken);

   List<QuoteDTO> fetchAllSP500Quotes(List<String> symbols, String apiToken);

   List<CompanyProfileDTO> fetchAllSP500CompanyProfiles(List<String> symbols, String apiToken);

}
