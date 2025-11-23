package com.bootcamp.demo.project_stock_data.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.bootcamp.demo.project_stock_data.entity.InfoEntity;

public interface InfoRepository extends JpaRepository<InfoEntity, String> {
    Optional<InfoEntity> findBySymbol(String symbol);
}
