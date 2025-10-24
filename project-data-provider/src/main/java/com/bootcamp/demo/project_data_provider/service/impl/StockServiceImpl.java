package com.bootcamp.demo.project_data_provider.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import com.bootcamp.demo.project_data_provider.finnhub.util.ApiUtils;
import com.bootcamp.demo.project_data_provider.model.dto.CompanyProfileDTO;
import com.bootcamp.demo.project_data_provider.model.dto.QuoteDTO;
import com.bootcamp.demo.project_data_provider.service.StockService;


@Service
public class StockServiceImpl implements StockService {

  @Value("${api-service.finnhub.api-token}")
  private String apiToken;
  private ApiUtils apiUtils;
  private RestTemplate restTemplate;

  public StockServiceImpl (String apiToken, ApiUtils apiUtils, RestTemplate restTemplate) {
    this.apiToken = apiToken;
    this.apiUtils = apiUtils;
    this.restTemplate = restTemplate;
  }

  @Override
  public QuoteDTO getQuote(String symbol, String apiToken) {
    String urlOfQuote =
       UriComponentsBuilder.newInstance() //
        .host(ApiUtils.finnhubHost) //
        .scheme("https") //
        .path(ApiUtils.quoteEndpoint) //
        .queryParam("symbol", symbol) //
        .build() //
        .toUriString();
    System.out.println("scheduleUrl = " + urlOfQuote);
    return this.restTemplate.getForObject(urlOfQuote, QuoteDTO.class);
  }

  @Override
  public CompanyProfileDTO getCompanyProfile(String symbol, String apiToken) {
    String urlOfCompanyProfile =
       UriComponentsBuilder.newInstance() //
        .host(ApiUtils.finnhubHost) //
        .scheme("https") //
        .path(ApiUtils.companyProfileEndpoint) //
        .queryParam("symbol", symbol) //
        .build() //
        .toUriString();
    System.out.println("scheduleUrl = " + urlOfCompanyProfile);
    return this.restTemplate.getForObject(urlOfCompanyProfile, CompanyProfileDTO.class);

  }



}
