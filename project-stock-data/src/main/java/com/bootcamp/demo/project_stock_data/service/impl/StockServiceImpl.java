package com.bootcamp.demo.project_stock_data.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import com.bootcamp.demo.project_stock_data.model.dto.ProfileDTO;
import com.bootcamp.demo.project_stock_data.model.dto.QuoteDTO;
import com.bootcamp.demo.project_stock_data.model.dto.SymbolDTO;
import com.bootcamp.demo.project_stock_data.service.StockService;
import com.bootcamp.demo.project_stock_data.util.ApiUtils;


@Service
public class StockServiceImpl implements StockService {

  // private static final Logger logger = LoggerFactory.getLogger(StockServiceImpl.class);

  @Value("${api-service.base-url}")
  private String apiBaseUrl;
  @Value("${api-service.api-token}")
  private String apiToken;

  @Autowired
  private RestTemplate restTemplate;

  //  private final ExecutorService executor;
  //  private final RateLimiter rateLimiter;          // 1 call per second per thread

  public StockServiceImpl(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;

    // 10 threads → max 600 calls/min (well under Finnhub free tier)
    // this.executor = Executors.newFixedThreadPool(10);

    // Google Guava RateLimiter (add dependency if you like)
    // this.rateLimiter = RateLimiter.create(1.0); // 1 permit per second
  }

  @Override
  public QuoteDTO getCurrentQuote(String symbol, String apiToken) {
    String urlOfQuote =
       UriComponentsBuilder.newInstance() //
        .scheme("https") //
        .host(ApiUtils.Finnhub.finnhubHost) //
        .path(ApiUtils.Finnhub.finnhubQuoteEndpoint) //
        .queryParam("symbol", symbol) //
        .queryParam("token", this.apiToken)
        .build() //
        .toUriString();
    System.out.println("Stock quote url = " + urlOfQuote);
    // return this.restTemplate.getForObject(urlOfQuote, QuoteDTO.class);
    try {
      QuoteDTO quoteDTO = this.restTemplate.getForObject(urlOfQuote, QuoteDTO.class);
      if (quoteDTO == null) 
        return null;
      return new QuoteDTO(
        symbol, // Use the input symbol
        quoteDTO.getPrice(),
        quoteDTO.getDayHigh(),
        quoteDTO.getDayLow(),
        quoteDTO.getDayOpen(),
        quoteDTO.getPreviousClosingPrice(),
        quoteDTO.getDatetime()
      );
    } catch (HttpClientErrorException e) {
      // Handle client error (4xx)
      System.err.println("Client error: " + e.getMessage());
      return null;
    } catch (Exception e) {
      // Handle other errors
      System.err.println("Error fetching quote: " + e.getMessage());
      return null;
    }
  }


  @Override
  public ProfileDTO getProfile(String symbol, String apiToken) {
    String urlOfProfile =
       UriComponentsBuilder.newInstance() //
        .scheme("https") //
        .host(ApiUtils.Finnhub.finnhubHost) //
        .path(ApiUtils.Finnhub.finnhubProfileEndpoint) //
        .queryParam("symbol", symbol) //
        .queryParam("token", this.apiToken)
        .build() //
        .toUriString();
    System.out.println("Stock profile url = " + urlOfProfile);
    // return this.restTemplate.getForObject(urlOfCompanyProfile, CompanyProfileDTO.class);
    try {
      return this.restTemplate.getForObject(urlOfProfile, ProfileDTO.class);
    } catch (Exception e) {
        // Log the error and return null or throw a custom exception
        System.err.println("Error fetching schedule: " + e.getMessage());
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
                continue; // Skip empty lines or comments
            }
            String[] parts = line.split("\\s+", 2); // Split on first whitespace
            if (parts.length == 2) {
                String symbol = parts[0].trim();
                String stockName = parts[1].trim();
                symbols.add(new SymbolDTO(symbol, stockName));
            } else {
                // Log warning or skip malformed line
                System.err.println("Skipping malformed line: " + line);
            }
        }
    } catch (IOException e) {
        throw new UncheckedIOException("Failed to read symbols file: " + filePath, e);
    }
    return symbols;
  }

}
