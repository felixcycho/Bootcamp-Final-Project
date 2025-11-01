package com.bootcamp.demo.project_data_provider.service.impl;

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
import com.bootcamp.demo.project_data_provider.finnhub.util.ApiUtils;
import com.bootcamp.demo.project_data_provider.model.dto.ProfileDTO;
import com.bootcamp.demo.project_data_provider.model.dto.QuoteDTO;
import com.bootcamp.demo.project_data_provider.model.dto.SymbolDTO;
import com.bootcamp.demo.project_data_provider.service.StockService;


@Service
public class StockServiceImpl implements StockService {

  // private static final Logger logger = LoggerFactory.getLogger(StockServiceImpl.class);

  @Value("${api-service.finnhub.api-token}")
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
                String companyName = parts[1].trim();
                symbols.add(new SymbolDTO(symbol, companyName));
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


  // @Override
  // public List<String> fetchSymbols(String apiToken) {
  //       List<String> symbols = new ArrayList<>();

  //       String listOfSymbols = 
  //         "C://github/Bootcamp-Final-Project/python/sp500_symbols.txt";

  //       try (BufferedReader br = new BufferedReader(new FileReader(listOfSymbols))) {
  //           String line;
  //           while ((line = br.readLine()) != null) {
  //               symbols.add(line.trim()); // Trim whitespace
  //           }
  //       } catch (IOException e) {
  //           e.printStackTrace(); // Handle the exception appropriately
  //       }
  //       return symbols;
  // }


  //   ! Not feasible, just for example only
  //   @Override
  //   public List<QuoteDTO> fetchAllSP500Quotes(List<String> symbols, String apiToken) {
  //         List<QuoteDTO> quoteList = new ArrayList<>();
  //         Set<String> failedSymbols = new HashSet<>();

  //         // First attempt to fetch data
  //         for (String symbol : symbols) {
  //             QuoteDTO quoteDTO = getCurrentQuote(symbol, apiToken);
  //             if (quoteDTO == null) {
  //                 failedSymbols.add(symbol);
  //             } else {
  //                 quoteList.add(quoteDTO);
  //             }
  //             // Sleep to respect API rate limits
  //             try {
  //                 Thread.sleep(10000); // 10 seconds between requests
  //             } catch (InterruptedException e) {
  //                 Thread.currentThread().interrupt(); // Restore interrupted status
  //                 logger.error("Thread was interrupted while sleeping: {}", e.getMessage());
  //             }
  //         }
  //         // Retry failed symbols
  //         int retryCount = 0;
  //         while (!failedSymbols.isEmpty() && retryCount < 10) { // Limit retries to 3 attempts
  //             logger.info("Retrying failed symbols: {}", failedSymbols);
  //             Set<String> currentFailures = new HashSet<>(failedSymbols);
  //             for (String symbol : currentFailures) {
  //                 QuoteDTO quoteDTO = getCurrentQuote(symbol, apiToken);
  //                 if (quoteDTO != null) {
  //                     quoteList.add(quoteDTO);
  //                     failedSymbols.remove(symbol); // Remove from failed set if successful
  //                 }
  //                 // Sleep to respect API rate limits
  //                 try {
  //                     Thread.sleep(10000); // 10 seconds between requests
  //                 } catch (InterruptedException e) {
  //                     Thread.currentThread().interrupt(); // Restore interrupted status
  //                     logger.error("Thread was interrupted while sleeping: {}", e.getMessage());
  //                 }
  //             }
  //             retryCount++;
  //         }
  //         // Log final status
  //         if (!failedSymbols.isEmpty()) {
  //             logger.warn("Failed to fetch data for the following symbols after retries: {}", failedSymbols);
  //         }
  //         return quoteList; // Return the list of successfully fetched quote data
  //       }


  //   ! Not feasible, just for example only
  //   @Override
  //   public List<ProfileDTO> fetchAllSP500Profiles(List<String> symbols, String apiToken) {
  //         List<ProfileDTO> profileList = new ArrayList<>();
  //         Set<String> failedSymbols = new HashSet<>();

  //         // First attempt to fetch data
  //         for (String symbol : symbols) {
  //             ProfileDTO profileDTO = getProfile(symbol, apiToken);
  //             if (profileDTO == null) {
  //                 failedSymbols.add(symbol);
  //             } else {
  //                 profileList.add(profileDTO);
  //             }

  //             // Sleep to respect API rate limits
  //             try {
  //                 Thread.sleep(10000); // 10 seconds between requests
  //             } catch (InterruptedException e) {
  //                 Thread.currentThread().interrupt(); // Restore interrupted status
  //                 logger.error("Thread was interrupted while sleeping: {}", e.getMessage());
  //             }
  //         }
  //         // Retry failed symbols
  //         int retryCount = 0;
  //         while (!failedSymbols.isEmpty() && retryCount < 10) { // Limit retries to 3 attempts
  //             logger.info("Retrying failed symbols: {}", failedSymbols);
  //             Set<String> currentFailures = new HashSet<>(failedSymbols);
  //             for (String symbol : currentFailures) {
  //                 ProfileDTO profileDTO = getProfile(symbol, apiToken);
  //                 if (profileDTO != null) {
  //                     profileList.add(profileDTO);
  //                     failedSymbols.remove(symbol); // Remove from failed set if successful
  //                 }
  //                 // Sleep to respect API rate limits
  //                 try {
  //                     Thread.sleep(10000); // 10 seconds between requests
  //                 } catch (InterruptedException e) {
  //                     Thread.currentThread().interrupt(); // Restore interrupted status
  //                     logger.error("Thread was interrupted while sleeping: {}", e.getMessage());
  //                 }
  //             }
  //             retryCount++;
  //         }
  //         // Log final status
  //         if (!failedSymbols.isEmpty()) {
  //             logger.warn("Failed to fetch data for the following symbols after retries: {}", failedSymbols);
  //         }
  //         return profileList; // Return the list of successfully fetched quote data
  //       }

}
