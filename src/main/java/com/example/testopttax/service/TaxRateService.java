package com.example.testopttax.service;

import com.example.testopttax.record.taxRate.TaxRateInput;
import com.example.testopttax.record.taxRate.TaxRateResponceDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TaxRateService {
    List<TaxRateResponceDto> getTaxRatesOfCountries();

    TaxRateResponceDto addTaxRate(TaxRateInput input);

    TaxRateResponceDto updateTaxRate(TaxRateInput input);
}
