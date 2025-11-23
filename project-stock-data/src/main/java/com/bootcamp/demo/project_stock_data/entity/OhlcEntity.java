package com.bootcamp.demo.project_stock_data.entity;

import java.io.Serializable;
import java.time.LocalDate;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
// import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
// import lombok.Setter;

@Entity
@Table(name = "sp500_ohlc_data")
@Getter 
@NoArgsConstructor 
@AllArgsConstructor
@Builder
public class OhlcEntity {
    @EmbeddedId
    private OhlcId ohlcId;
    private Double value;

    @Embeddable
    @Getter 
    @NoArgsConstructor 
    @AllArgsConstructor
    @EqualsAndHashCode
    public static class OhlcId implements Serializable {

        private String symbol;
        @Column(name = "date")
        private LocalDate date;
        private String metric;
    }


    // Optional: helper methods
    public String getSymbol() { return ohlcId.symbol != null ? ohlcId.getSymbol() : null; }
    public LocalDate getDate() { return ohlcId.date != null ? ohlcId.getDate() : null; }
    public String getMetric() { return ohlcId.metric != null ? ohlcId.getMetric() : null; }

}