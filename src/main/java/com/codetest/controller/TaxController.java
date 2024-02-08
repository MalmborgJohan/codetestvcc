package com.codetest.controller;

import com.codetest.api.TaxCalculationRequest;
import org.springframework.web.bind.annotation.*;
import com.codetest.service.CongestionTaxCalculator;

@RestController
public class TaxController {

    private final CongestionTaxCalculator congestionTaxCalculator;

    public TaxController(CongestionTaxCalculator congestionTaxCalculator) {
        this.congestionTaxCalculator = congestionTaxCalculator;
    }

    @PostMapping("/calculate")
    public int getTax(@RequestBody TaxCalculationRequest taxCalculationRequest) {
       return congestionTaxCalculator.getTax(taxCalculationRequest.getVehicle(), taxCalculationRequest.getDates());
    }
}
