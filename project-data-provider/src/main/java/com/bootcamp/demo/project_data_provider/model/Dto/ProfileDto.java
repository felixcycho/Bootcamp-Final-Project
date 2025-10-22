package com.bootcamp.demo.project_data_provider.model.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProfileDto {
  private String symbol;
    private String name;
    private String logo;
    private String marketCap;
    private String industry;
    // Getters/Setters
}
