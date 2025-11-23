package com.bootcamp.demo.project_stock_data.service;

import java.time.LocalDate;
import java.util.List;
import com.bootcamp.demo.project_stock_data.model.dto.InfoDTO;
import com.bootcamp.demo.project_stock_data.model.dto.OhlcDTO;
import com.bootcamp.demo.project_stock_data.model.dto.ProfileDTO;
import com.bootcamp.demo.project_stock_data.model.dto.QuoteDTO;
import com.bootcamp.demo.project_stock_data.model.dto.SymbolDTO;
import io.micrometer.common.lang.Nullable;

public interface StockService {

   List<SymbolDTO> fetchSymbols();

   QuoteDTO getQuote(String symbol, String apiToken);

   ProfileDTO getProfile(String symbol);

   InfoDTO getInfo(String symbol);

   List<OhlcDTO> getOhlcs(
      String symbol, 
      @Nullable LocalDate startDate, 
      @Nullable LocalDate endDate);

}
