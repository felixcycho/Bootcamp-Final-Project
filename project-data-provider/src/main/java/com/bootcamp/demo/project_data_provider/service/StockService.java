package com.bootcamp.demo.project_data_provider.service;

import java.util.List;
import com.bootcamp.demo.project_data_provider.model.dto.ProfileDTO;
import com.bootcamp.demo.project_data_provider.model.dto.QuoteDTO;
import com.bootcamp.demo.project_data_provider.model.dto.SymbolDTO;

public interface StockService {

   QuoteDTO getCurrentQuote(String symbol, String apiToken);

   ProfileDTO getProfile(String symbol, String apiToken);

   // List<String> fetchSymbols(String apiToken);
   List<SymbolDTO> fetchSymbols();

   // ! Not feasible, just for example only
   // List<QuoteDTO> fetchAllSP500Quotes(List<String> symbols, String apiToken);

   // ! Not feasible, just for example only
   // List<ProfileDTO> fetchAllSP500Profiles(List<String> symbols, String apiToken);

}
