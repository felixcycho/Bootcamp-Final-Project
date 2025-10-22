package com.bootcamp.demo.project_data_provider.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.bootcamp.demo.project_data_provider.model.Dto.ProfileDto;
import com.bootcamp.demo.project_data_provider.model.Dto.QuoteDto;

@Service
public class DataProviderService {
  private final RestTemplate restTemplate = new RestTemplate();
    @Value("${finnhub.api.key}")
    private String apiKey;

    public QuoteDto getQuote(String symbol) {
        String url = "https://finnhub.io/api/v1/quote?symbol=" + symbol.toUpperCase() + "&token=" + apiKey;
        return restTemplate.getForObject(url, QuoteDto.class);
    }

    public ProfileDto getProfile(String symbol) {
        String url = "https://finnhub.io/api/v1/stock/profile2?symbol=" + symbol + "&token=" + apiKey;
        return restTemplate.getForObject(url, ProfileDto.class);
    }
}
