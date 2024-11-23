package com.example.testopttax.service.strategy;

import com.example.testopttax.model.Country;
import com.example.testopttax.model.TaxRate;
import com.example.testopttax.model.User;
import com.example.testopttax.model.UserIncome;
import com.example.testopttax.record.report.CountryReportResponceDto;
import com.example.testopttax.record.report.IncomeDetailResponceDto;
import com.example.testopttax.service.IncomeCategoryService;
import com.example.testopttax.service.TaxRateService;
import com.example.testopttax.service.UserIncomeService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public interface ReportFormatStrategy {
    CountryReportResponceDto getReport(User user, Country country);



    default Map<Long, BigDecimal> getTaxRatesByCountry(Country country, TaxRateService taxRateService) {
        List<TaxRate> taxRates = taxRateService.getTaxRateByCountry(country);
        return taxRates.stream()
                .collect(Collectors.toMap(
                        rate -> rate.getIncomeCategory().getId(),
                        TaxRate::getRate
                ));
    }
    default List<IncomeDetailResponceDto> calculateIncomeDetails(User user, Map<Long, BigDecimal> taxRateMap,
                                                                 UserIncomeService userIncomeService,
                                                                 IncomeCategoryService incomeCategoryService) {
        List<UserIncome> userIncomes = userIncomeService.getUserIncomesByUser(user);
        return userIncomes.stream()
                .map(income -> {
                    BigDecimal incomeAmount = income.getAmount();
                    BigDecimal taxRate = taxRateMap.getOrDefault(income.getIncomeCategory().getId(), BigDecimal.ZERO);
                    BigDecimal taxAmount = incomeAmount.multiply(taxRate)
                            .divide(BigDecimal.valueOf(100), RoundingMode.HALF_UP);

                    return new IncomeDetailResponceDto(
                            incomeCategoryService.mapModelToResponceDto(income.getIncomeCategory()),
                            incomeAmount,
                            taxAmount
                    );
                })
                .toList();
    }
}
