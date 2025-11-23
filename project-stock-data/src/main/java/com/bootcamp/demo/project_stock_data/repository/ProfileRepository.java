package com.bootcamp.demo.project_stock_data.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.bootcamp.demo.project_stock_data.entity.ProfileEntity;

public interface ProfileRepository extends JpaRepository<ProfileEntity, String> {
    Optional<ProfileEntity> findBySymbol(String symbol);
}
