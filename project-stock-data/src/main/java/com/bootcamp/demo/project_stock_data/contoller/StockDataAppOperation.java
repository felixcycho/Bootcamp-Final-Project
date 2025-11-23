package com.bootcamp.demo.project_stock_data.contoller;

import java.time.LocalDate;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.bootcamp.demo.project_stock_data.model.dto.InfoDTO;
import com.bootcamp.demo.project_stock_data.model.dto.OhlcDTO;
import com.bootcamp.demo.project_stock_data.model.dto.ProfileDTO;
import com.bootcamp.demo.project_stock_data.model.dto.QuoteDTO;
import com.bootcamp.demo.project_stock_data.model.dto.SymbolDTO;

public interface StockDataAppOperation {
  @GetMapping(value = "/fetch/symbols")
  List<SymbolDTO> fetchSymbols();

  @GetMapping(value = "/get/quote")
  QuoteDTO getQuote(@RequestParam String symbol, @RequestParam String apiToken);
  
  @GetMapping(value = "/get/profile")
  ProfileDTO getProfile(@RequestParam String symbol);

  @GetMapping(value = "/get/info")
  InfoDTO getInfo(@RequestParam String symbol);

  @GetMapping(value = "/get/ohlcs")
  List<OhlcDTO> getOhlcs(
    @RequestParam String symbol, 
    @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate, 
    @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate);
  
}