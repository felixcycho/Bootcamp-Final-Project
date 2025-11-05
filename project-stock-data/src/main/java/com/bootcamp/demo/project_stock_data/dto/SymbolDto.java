package com.bootcamp.demo.project_stock_data.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SymbolDto {
  String symbol;
  String stockName;
}
