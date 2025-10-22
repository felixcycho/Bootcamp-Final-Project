package com.bootcamp.demo.project_data_provider.model.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QuoteDto {
  private String symbol;
    private Double c;  // Current price
    private Double d;  // Change
    private Double dp; // Change %
    private Long v;    // Volume
    // Getters/Setters
}
