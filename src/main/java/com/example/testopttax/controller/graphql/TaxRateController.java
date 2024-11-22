package com.example.testopttax.controller.graphql;

import com.example.testopttax.record.taxRate.TaxRateInput;
import com.example.testopttax.record.taxRate.TaxRateResponceDto;
import com.example.testopttax.service.TaxRateService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
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

    @MutationMapping
    public TaxRateResponceDto addTaxRate(@Argument("input")TaxRateInput input){
        return taxRateService.addTaxRate(input);
    }

    @MutationMapping
    public TaxRateResponceDto updateTaxRate(@Argument("input") TaxRateInput input){
        return taxRateService.updateTaxRate(input);
    }
}
