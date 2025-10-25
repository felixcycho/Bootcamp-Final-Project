package com.bootcamp.demo.project_data_provider.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.UnknownContentTypeException;
import org.springframework.web.util.UriComponentsBuilder;
import com.bootcamp.demo.project_data_provider.finnhub.util.ApiUtils;
import com.bootcamp.demo.project_data_provider.model.dto.CompanyProfileDTO;
import com.bootcamp.demo.project_data_provider.model.dto.QuoteDTO;
import com.bootcamp.demo.project_data_provider.service.StockService;


@Service
public class StockServiceImpl implements StockService {

  @Value("${api-service.finnhub.api-token}")
  private String apiToken;
  @Autowired
  private RestTemplate restTemplate;

  @Override
  public QuoteDTO getQuote(String symbol, String apiToken) {
    String urlOfQuote =
       UriComponentsBuilder.newInstance() //
        .host(ApiUtils.finnhubHost) //
        .scheme("https") //
        .path(ApiUtils.quoteEndpoint) //
        .queryParam("symbol", symbol) //
        .queryParam("apiToken", apiToken)
        .build() //
        .toUriString();
    System.out.println("scheduleUrl = " + urlOfQuote);
    try {
      return this.restTemplate.getForObject(urlOfQuote, QuoteDTO.class);
    } catch (Exception e) {
        // Log the error and return null or throw a custom exception
        System.err.println("Error fetching schedule: " + e.getMessage());
        return null;
    }
    
  }

  @Override
  public CompanyProfileDTO getCompanyProfile(String symbol, String apiToken) {
    String urlOfCompanyProfile =
       UriComponentsBuilder.newInstance() //
        .host(ApiUtils.finnhubHost) //
        .scheme("https") //
        .path(ApiUtils.companyProfileEndpoint) //
        .queryParam("symbol", symbol) //
        .queryParam("apiToken", apiToken)
        .build() //
        .toUriString();
    System.out.println("scheduleUrl = " + urlOfCompanyProfile);
    try {
      return this.restTemplate.getForObject(urlOfCompanyProfile, CompanyProfileDTO.class);
    } catch (Exception e) {
        // Log the error and return null or throw a custom exception
        System.err.println("Error fetching schedule: " + e.getMessage());
        return null;
    }

  }

}
