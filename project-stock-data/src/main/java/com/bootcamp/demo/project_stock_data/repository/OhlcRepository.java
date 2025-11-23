package com.bootcamp.demo.project_stock_data.repository;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.bootcamp.demo.project_stock_data.entity.OhlcEntity;
import com.bootcamp.demo.project_stock_data.entity.OhlcEntity.OhlcId;

public interface OhlcRepository extends JpaRepository<OhlcEntity, OhlcId> {
    // Symbol + date range (inclusive)
    List<OhlcEntity> findBySymbolAndDate(
      String symbol, LocalDate startDate, LocalDate endDate);
}
