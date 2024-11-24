package com.example.testopttax.service.impl;

import com.example.testopttax.exception.CustomException;
import com.example.testopttax.model.Country;
import com.example.testopttax.model.IncomeCategory;
import com.example.testopttax.model.TaxRate;
import com.example.testopttax.record.taxRate.TaxRateInput;
import com.example.testopttax.record.taxRate.TaxRateResponceDto;
import com.example.testopttax.repo.TaxRateRepository;
import com.example.testopttax.service.CountryService;
import com.example.testopttax.service.IncomeCategoryService;
import com.example.testopttax.service.TaxRateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TaxRateServiceImpl implements TaxRateService {
    private final TaxRateRepository taxRateRepository;
    private final CountryService countryService;
    private final IncomeCategoryService categoryService;
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

    @Override
    public TaxRateResponceDto addTaxRate(TaxRateInput input) {
        if (taxRateRepository.findTaxRateByCountryIdAndIncomeCategoryId(input.countryId(), input.incomeCategoryId()).isPresent()){
            log.error("Уже существует налоговая ставка для данной категории и страны!");
            throw new CustomException("Уже существует налоговая ставка для данной категории и страны, используйте другой метод для обновления!");
        }
        BigDecimal rate = bigDecimalFromFloat(input.rate());

        Country country = countryService.findById(input.countryId());
        IncomeCategory incomeCategory = categoryService.findById(input.incomeCategoryId());

        TaxRate taxRate = new TaxRate();
        taxRate.setCountry(country);
        taxRate.setIncomeCategory(incomeCategory);
        taxRate.setRate(rate);

        TaxRate taxRateSaved = taxRateRepository.save(taxRate);

        return mapModelToResponceDto(taxRateSaved);
    }

    @Override
    public TaxRateResponceDto updateTaxRate(TaxRateInput input) {
        TaxRate taxRate = taxRateRepository.findTaxRateByCountryIdAndIncomeCategoryId(input.countryId(), input.incomeCategoryId()).orElseThrow(() -> new CustomException("Не найдена запись налоговых ставок по идентификатору страны и категории дохода"));
        BigDecimal rate = bigDecimalFromFloat(input.rate());

        taxRate.setRate(rate);

        TaxRate updatedTaxRate = taxRateRepository.save(taxRate);
        return mapModelToResponceDto(updatedTaxRate);
    }

    @Override
    public List<TaxRate> getTaxRateByCountry(Country country) {
        return taxRateRepository.findTaxRatesByCountry(country);
    }

    public TaxRateResponceDto mapModelToResponceDto(TaxRate taxRate){
        return new TaxRateResponceDto(taxRate.getId(),taxRate.getCreatedAt(),taxRate.getUpdatedAt(),countryService.mapModelToResponceDto(taxRate.getCountry()),categoryService.mapModelToResponceDto(taxRate.getIncomeCategory()),taxRate.getRate());
    }

    private BigDecimal bigDecimalFromFloat(Float data){
        if (data > 999){
            log.error("Ввели слишком большое значени для процентной ставки налога!");
            throw new CustomException("Процентная ставка должна быть меньше 999)");
        }
        return BigDecimal.valueOf(data).setScale(2, RoundingMode.HALF_UP);
    }

}
