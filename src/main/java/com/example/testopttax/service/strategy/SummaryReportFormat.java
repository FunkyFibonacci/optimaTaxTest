package com.example.testopttax.service.strategy;

import com.example.testopttax.model.Country;
import com.example.testopttax.model.TaxRate;
import com.example.testopttax.model.User;
import com.example.testopttax.model.UserIncome;
import com.example.testopttax.record.report.CountryReportResponceDto;
import com.example.testopttax.service.TaxRateService;
import com.example.testopttax.service.UserIncomeService;
import com.example.testopttax.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class SummaryReportFormat implements ReportFormatStrategy {

    private final TaxRateService taxRateService;
    private final UserIncomeService userIncomeService;
    private final CountryService countryService;

    @Override
    public CountryReportResponceDto getReport(User user, Country country) {
        Map<Long, BigDecimal> taxRateMap = getTaxRatesByCountry(country, taxRateService);
        List<UserIncome> userIncomes = userIncomeService.getUserIncomesByUser(user);

        BigDecimal totalIncome = BigDecimal.ZERO;
        BigDecimal totalTax = BigDecimal.ZERO;

        for (UserIncome income : userIncomes) {
            BigDecimal incomeAmount = income.getAmount();
            BigDecimal taxRate = taxRateMap.getOrDefault(income.getIncomeCategory().getId(), BigDecimal.ZERO);
            BigDecimal taxAmount = incomeAmount.multiply(taxRate).divide(BigDecimal.valueOf(100), RoundingMode.HALF_UP);

            totalIncome = totalIncome.add(incomeAmount);
            totalTax = totalTax.add(taxAmount);
        }

        return new CountryReportResponceDto(countryService.mapModelToResponceDto(country), totalIncome, totalTax, null, null);
    }


}
