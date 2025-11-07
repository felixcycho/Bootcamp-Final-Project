package com.bootcamp.demo.project_stock_data.util;

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

    // // ← ADD THIS NEW CLASS (only 8 lines)
    // public final static class InternalProvider {
    //     public static final String BASE_PATH_QUOTE = "/get/current_quote";
    //     public static final String BASE_PATH_PROFILE = "/get/profile";

    //     public static String quoteUrl(String baseUrl, String symbol, String apiToken) {
    //         return "http://" + baseUrl + BASE_PATH_QUOTE + "?symbol=" + symbol + "&apiToken=" + apiToken;
    //     }

    //     public static String profileUrl(String baseUrl, String symbol, String apiToken) {
    //         return "http://" + baseUrl + BASE_PATH_PROFILE + "?symbol=" + symbol + "&apiToken=" + apiToken;
    //     }
    // }
}
