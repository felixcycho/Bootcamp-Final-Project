package com.bootcamp.demo.project_stock_data.service.impl;

import com.bootcamp.demo.project_stock_data.model.dto.ProfileDTO;
import com.bootcamp.demo.project_stock_data.model.dto.QuoteDTO;
import com.bootcamp.demo.project_stock_data.model.dto.SymbolDTO;
import com.bootcamp.demo.project_stock_data.service.StockService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class StockServiceImpl implements StockService {

    @Value("${app.provider.base-url}")
    private String baseUrl;

    @Value("${app.provider.api-token}")
    private String apiToken;

    private final RestTemplate restTemplate;

    public StockServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public QuoteDTO getCurrentQuote(String symbol, String apiToken) {
        String url = UriComponentsBuilder.newInstance()
                .scheme("http")
                .host(baseUrl)                    // e.g. "localhost:8090" or "provider-data-provider:8090"
                .path("/get/current_quote")
                .queryParam("symbol", symbol)
                .queryParam("apiToken", this.apiToken)  // FIXED: was "token"
                .build()
                .toUriString();

        log.info("Fetching quote from provider: {}", url);

        try {
            QuoteDTO quoteDTO = restTemplate.getForObject(url, QuoteDTO.class);
            if (quoteDTO == null) {
                log.warn("Provider returned null for symbol: {}", symbol);
                return null;
            }
            return new QuoteDTO(
                    symbol,
                    quoteDTO.getPrice(),
                    quoteDTO.getDayHigh(),
                    quoteDTO.getDayLow(),
                    quoteDTO.getDayOpen(),
                    quoteDTO.getPreviousClosingPrice(),
                    LocalDateTime.now()
            );
        } catch (HttpClientErrorException e) {
            log.error("HTTP {} error from provider for symbol {}: {}", e.getStatusCode(), symbol, e.getMessage());
            return null;
        } catch (Exception e) {
            log.error("Unexpected error fetching quote for {}: {}", symbol, e.toString());
            return null;
        }
    }

    @Override
    public ProfileDTO getProfile(String symbol, String apiToken) {
        String url = UriComponentsBuilder.newInstance()
                .scheme("http")
                .host(baseUrl)
                .path("/get/profile")
                .queryParam("symbol", symbol)
                .queryParam("apiToken", this.apiToken)  // FIXED: was "token"
                .build()
                .toUriString();

        log.info("Fetching profile from provider: {}", url);

        try {
            ProfileDTO profileDTO = restTemplate.getForObject(url, ProfileDTO.class);
            if (profileDTO == null) {
                log.warn("Provider returned null for symbol: {}", symbol);
                return null;
            }
            return new ProfileDTO(
              symbol, // Use the input symbol
              profileDTO.getCurrency(),
              profileDTO.getExchange(),
              profileDTO.getMarketCapUsdMillions(),
              profileDTO.getStockName(),
              profileDTO.getTicker(),
              profileDTO.getMainIndustry(),
              LocalDateTime.now()
           );
        } catch (Exception e) {
            log.error("Failed to fetch profile for {}: {}", symbol, e.toString());
            return null;
        }
    }

    @Override
    public List<SymbolDTO> fetchSymbols() {
        List<SymbolDTO> symbols = new ArrayList<>();
        Path filePath = Path.of("C:/github/Bootcamp-Final-Project/python/sp500_symbols.txt");

        if (!Files.exists(filePath)) {
            throw new IllegalStateException("File not found: " + filePath);
        }

        try (BufferedReader br = Files.newBufferedReader(filePath, StandardCharsets.UTF_8)) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty() || line.startsWith("#")) {
                    continue;
                }
                String[] parts = line.split("\\s+", 2);
                if (parts.length == 2) {
                    String symbol = parts[0].trim();
                    String stockName = parts[1].trim();
                    symbols.add(new SymbolDTO(symbol, stockName));
                } else {
                    log.warn("Skipping malformed line in symbols file: {}", line);
                }
            }
        } catch (IOException e) {
            throw new UncheckedIOException("Failed to read symbols file: " + filePath, e);
        }

        log.info("Loaded {} symbols from {}", symbols.size(), filePath);
        return symbols;
    }
}