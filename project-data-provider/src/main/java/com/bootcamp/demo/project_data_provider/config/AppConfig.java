package com.bootcamp.demo.project_data_provider.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import com.bootcamp.demo.project_data_provider.finnhub.service.StockService;
import com.bootcamp.demo.project_data_provider.finnhub.service.impl.StockServiceImpl;

@Configuration
public class AppConfig {
  @Bean
  RestTemplate restTemplate() {
    return new RestTemplate();
  }

  @Bean
  StockService stockService(RestTemplate restTemplate) {
    return new StockServiceImpl(restTemplate);
  };
  
}
