package com.bootcamp.demo.project_stock_data.service;

import java.util.List;
import com.bootcamp.demo.project_stock_data.model.dto.ProfileDTO;
import com.bootcamp.demo.project_stock_data.model.dto.QuoteDTO;
import com.bootcamp.demo.project_stock_data.model.dto.SymbolDTO;

public interface StockService {

   QuoteDTO getQuote(String symbol, String apiToken);

   ProfileDTO getProfile(String symbol, String apiToken);

   // List<String> fetchSymbols(String apiToken);
   List<SymbolDTO> fetchSymbols();

}
