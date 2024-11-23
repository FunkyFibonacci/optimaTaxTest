package com.example.testopttax.service.strategy;

import com.example.testopttax.model.Country;
import com.example.testopttax.model.User;
import com.example.testopttax.record.report.CountryReportResponceDto;
import com.example.testopttax.record.report.IncomeDetailResponceDto;
import com.example.testopttax.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.UUID;
@Component
@RequiredArgsConstructor
public class SignatureReportFormat implements ReportFormatStrategy {

    private final UserService userService;
    private final CountryService countryService;
    private final TaxRateService taxRateService;
    private final UserIncomeService userIncomeService;
    private final IncomeCategoryService incomeCategoryService;

    @Override
    public CountryReportResponceDto getReport(User user, Country country) {
        Map<Long, BigDecimal> taxRateMap = getTaxRatesByCountry(country, taxRateService);
        List<IncomeDetailResponceDto> incomeDetails = calculateIncomeDetails(user, taxRateMap, userIncomeService, incomeCategoryService);

        return buildCountryReportWithUUID(country, incomeDetails);
    }

    private CountryReportResponceDto buildCountryReportWithUUID(Country country, List<IncomeDetailResponceDto> incomeDetails) {
        BigDecimal totalIncome = incomeDetails.stream()
                .map(IncomeDetailResponceDto::income)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalTax = incomeDetails.stream()
                .map(IncomeDetailResponceDto::tax)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        String signature = UUID.randomUUID().toString();

        return new CountryReportResponceDto(
                countryService.mapModelToResponceDto(country),
                totalIncome,
                totalTax,
                incomeDetails,
                signature
        );
    }
}