package com.bootcamp.demo.project_data_provider.util;

import java.time.LocalDateTime;

public class Timestamp {
  private LocalDateTime currentTime;

  public Timestamp(LocalDateTime currentTime) {
      this.currentTime = currentTime;
  }

  // Method to get the current timestamp
  public LocalDateTime now() {
    currentTime = LocalDateTime.now();
    return currentTime;
  }

  // Method to get the timestamp for one year ago
  public LocalDateTime oneYearAgo() {
    LocalDateTime oneYearAgo = LocalDateTime.now().minusYears(1);
    return oneYearAgo;
  }

  // Method to get the timestamp for two years ago
  public LocalDateTime twoYearsAgo() {
    LocalDateTime twoYearsAgo = LocalDateTime.now().minusYears(2);
    return twoYearsAgo;
  }


}
