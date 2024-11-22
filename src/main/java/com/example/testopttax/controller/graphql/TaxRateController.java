package com.example.testopttax.controller.graphql;

import com.example.testopttax.record.taxRate.TaxRateResponceDto;
import com.example.testopttax.service.TaxRateService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class TaxRateController {
    private final TaxRateService taxRateService;
    @QueryMapping
    public List<TaxRateResponceDto> getTaxRatesOfCountries(){
        return taxRateService.getTaxRatesOfCountries();
    }
}
