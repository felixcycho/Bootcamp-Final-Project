package com.bootcamp.demo.project_data_provider.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SymbolDto {
  String symbol;
  String companyName;
}
