package com.bootcamp.demo.project_data_provider.service.impl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import com.google.common.util.concurrent.RateLimiter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import com.bootcamp.demo.project_data_provider.finnhub.util.ApiUtils;
import com.bootcamp.demo.project_data_provider.model.dto.CompanyProfileDTO;
import com.bootcamp.demo.project_data_provider.model.dto.QuoteDTO;
import com.bootcamp.demo.project_data_provider.service.StockService;


@Service
public class StockServiceImpl implements StockService {

  private static final Logger logger = LoggerFactory.getLogger(StockServiceImpl.class);

  @Value("${api-service.finnhub.api-token}")
  private String apiToken;

  @Autowired
  private RestTemplate restTemplate;

  private final ExecutorService executor;
  private final RateLimiter rateLimiter;          // 1 call per second per thread

  public StockServiceImpl(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;

    // 10 threads → max 600 calls/min (well under Finnhub free tier)
    this.executor = Executors.newFixedThreadPool(10);

    // Google Guava RateLimiter (add dependency if you like)
    this.rateLimiter = RateLimiter.create(1.0); // 1 permit per second
  }

  @Override
  public QuoteDTO getQuote(String symbol, String apiToken) {
    String urlOfQuote =
       UriComponentsBuilder.newInstance() //
        .scheme("https") //
        .host(ApiUtils.finnhubHost) //
        .path(ApiUtils.finnhubQuoteEndpoint) //
        .queryParam("symbol", symbol) //
        .queryParam("token", this.apiToken)
        .build() //
        .toUriString();
    System.out.println("Quote Url = " + urlOfQuote);
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
  public CompanyProfileDTO getCompanyProfile(String symbol, String apiToken) {
    String urlOfCompanyProfile =
       UriComponentsBuilder.newInstance() //
        .scheme("https") //
        .host(ApiUtils.finnhubHost) //
        .path(ApiUtils.finnhubCompanyProfileEndpoint) //
        .queryParam("symbol", symbol) //
        .queryParam("token", this.apiToken)
        .build() //
        .toUriString();
    System.out.println("Company Profile Url = " + urlOfCompanyProfile);
    // return this.restTemplate.getForObject(urlOfCompanyProfile, CompanyProfileDTO.class);
    try {
      return this.restTemplate.getForObject(urlOfCompanyProfile, CompanyProfileDTO.class);
    } catch (Exception e) {
        // Log the error and return null or throw a custom exception
        System.err.println("Error fetching schedule: " + e.getMessage());
        return null;
    }
  }


  @Override
  public List<String> fetchSymbols(String apiToken) {
        List<String> symbols = new ArrayList<>();

        String listOfSymbols = 
          "C://github/Bootcamp-Final-Project/python/sp500_symbols.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(listOfSymbols))) {
            String line;
            while ((line = br.readLine()) != null) {
                symbols.add(line.trim()); // Trim whitespace
            }
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
        return symbols;
  }


  @Override
  public List<QuoteDTO> fetchAllSP500Quotes(List<String> symbols, String apiToken) {
        List<QuoteDTO> quoteList = new ArrayList<>();
        Set<String> failedSymbols = new HashSet<>();

        // First attempt to fetch data
        for (String symbol : symbols) {
            QuoteDTO quoteDTO = getQuote(symbol, apiToken);
            if (quoteDTO == null) {
                failedSymbols.add(symbol);
            } else {
                quoteList.add(quoteDTO);
            }
            // Sleep to respect API rate limits
            try {
                Thread.sleep(10000); // 10 seconds between requests
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Restore interrupted status
                logger.error("Thread was interrupted while sleeping: {}", e.getMessage());
            }
        }
        // Retry failed symbols
        int retryCount = 0;
        while (!failedSymbols.isEmpty() && retryCount < 10) { // Limit retries to 3 attempts
            logger.info("Retrying failed symbols: {}", failedSymbols);
            Set<String> currentFailures = new HashSet<>(failedSymbols);
            for (String symbol : currentFailures) {
                QuoteDTO quoteDTO = getQuote(symbol, apiToken);
                if (quoteDTO != null) {
                    quoteList.add(quoteDTO);
                    failedSymbols.remove(symbol); // Remove from failed set if successful
                }
                // Sleep to respect API rate limits
                try {
                    Thread.sleep(10000); // 10 seconds between requests
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt(); // Restore interrupted status
                    logger.error("Thread was interrupted while sleeping: {}", e.getMessage());
                }
            }
            retryCount++;
        }
        // Log final status
        if (!failedSymbols.isEmpty()) {
            logger.warn("Failed to fetch data for the following symbols after retries: {}", failedSymbols);
        }
        return quoteList; // Return the list of successfully fetched quote data
      }

  @Override
  public List<CompanyProfileDTO> fetchAllSP500CompanyProfiles(List<String> symbols, String apiToken) {
        List<CompanyProfileDTO> companyProfileList = new ArrayList<>();
        Set<String> failedSymbols = new HashSet<>();

        // First attempt to fetch data
        for (String symbol : symbols) {
            CompanyProfileDTO companyProfileDTO = getCompanyProfile(symbol, apiToken);
            if (companyProfileDTO == null) {
                failedSymbols.add(symbol);
            } else {
                companyProfileList.add(companyProfileDTO);
            }

            // Sleep to respect API rate limits
            try {
                Thread.sleep(10000); // 10 seconds between requests
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Restore interrupted status
                logger.error("Thread was interrupted while sleeping: {}", e.getMessage());
            }
        }
        // Retry failed symbols
        int retryCount = 0;
        while (!failedSymbols.isEmpty() && retryCount < 10) { // Limit retries to 3 attempts
            logger.info("Retrying failed symbols: {}", failedSymbols);
            Set<String> currentFailures = new HashSet<>(failedSymbols);
            for (String symbol : currentFailures) {
                CompanyProfileDTO companyProfileDTO = getCompanyProfile(symbol, apiToken);
                if (companyProfileDTO != null) {
                    companyProfileList.add(companyProfileDTO);
                    failedSymbols.remove(symbol); // Remove from failed set if successful
                }
                // Sleep to respect API rate limits
                try {
                    Thread.sleep(10000); // 10 seconds between requests
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt(); // Restore interrupted status
                    logger.error("Thread was interrupted while sleeping: {}", e.getMessage());
                }
            }
            retryCount++;
        }
        // Log final status
        if (!failedSymbols.isEmpty()) {
            logger.warn("Failed to fetch data for the following symbols after retries: {}", failedSymbols);
        }
        return companyProfileList; // Return the list of successfully fetched quote data
      }

}
