package com.bootcamp.demo.project_data_provider.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.bootcamp.demo.project_data_provider.model.Dto.ProfileDto;
import com.bootcamp.demo.project_data_provider.model.Dto.QuoteDto;
import com.bootcamp.demo.project_data_provider.service.DataProviderService;
import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestMethod;
// import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("api")
public class DataProviderController {
  @Autowired private DataProviderService dataProviderService;

    @GetMapping("/quote/{symbol}")
    public ResponseEntity<QuoteDto> getQuote(@PathVariable String symbol) {
        return ResponseEntity.ok(dataProviderService.getQuote(symbol));
    }

    @GetMapping("/profile/{symbol}")
    public ResponseEntity<ProfileDto> getProfile(@PathVariable String symbol) {
        return ResponseEntity.ok(dataProviderService.getProfile(symbol));
    }
}
