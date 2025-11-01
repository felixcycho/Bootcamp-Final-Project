package com.bootcamp.demo.project_data_provider.finnhub.util;

public final class ApiUtils {
  public final static class Finnhub {
      public static final String finnhubHost = "finnhub.io";
      public static final String finnhubQuoteEndpoint = "/api/v1/quote";
      public static final String finnhubProfileEndpoint = "/api/v1/stock/profile2";
  }
  public final static class RawGithub {
      public static final String rawGithubUserHost = "raw.githubusercontent.com";
      public static final String rawGithubUserEndpoint = "/datasets/s-and-p-500-companies/master/data/constituents.csv";
  }

}
