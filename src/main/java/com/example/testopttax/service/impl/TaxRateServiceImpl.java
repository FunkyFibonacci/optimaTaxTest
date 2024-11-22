package com.example.testopttax.service.impl;

import com.example.testopttax.model.TaxRate;
import com.example.testopttax.record.taxRate.TaxRateResponceDto;
import com.example.testopttax.repo.TaxRateRepository;
import com.example.testopttax.service.TaxRateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TaxRateServiceImpl implements TaxRateService {
    private final TaxRateRepository taxRateRepository;
    private final CountryServiceImpl countryService;
    private final IncomeCategoryServiceImpl categoryService;
    @Override
    public List<TaxRateResponceDto> getTaxRatesOfCountries() {
        List<TaxRate> taxRates = taxRateRepository.findAll();

        return taxRates.stream()
                .map(taxRate -> new TaxRateResponceDto(
                        taxRate.getId(),
                        taxRate.getCreatedAt(),
                        taxRate.getUpdatedAt(),
                        countryService.mapModelToResponceDto(taxRate.getCountry()),
                        categoryService.mapModelToResponceDto(taxRate.getIncomeCategory()) ,
                        taxRate.getRate()
                ))
                .toList();
    }

}
