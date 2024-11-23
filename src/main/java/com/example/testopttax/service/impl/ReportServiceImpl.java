package com.example.testopttax.service.impl;

import com.example.testopttax.model.Country;
import com.example.testopttax.model.TaxRate;
import com.example.testopttax.model.User;
import com.example.testopttax.model.UserIncome;
import com.example.testopttax.record.report.CountryReportResponceDto;
import com.example.testopttax.record.report.IncomeDetailResponceDto;
import com.example.testopttax.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {
    private final UserService userService;
    private final TaxRateService taxRateService;
    private final CountryService countryService;
    private final UserIncomeService userIncomeService;
    private final IncomeCategoryService incomeCategoryService;

    @Override
    public CountryReportResponceDto getReportByCountryId(Long id) {
        User user = userService.getUserBySecurityHolder(SecurityContextHolder.getContext().getAuthentication());
        Country country = countryService.findById(id);
        Map<Long, BigDecimal> taxRateMap = getTaxRatesByCountry(country);
        List<IncomeDetailResponceDto> incomeDetails = calculateIncomeDetails(user, taxRateMap);
        return buildCountryReport(country, incomeDetails);
    }


    private Map<Long, BigDecimal> getTaxRatesByCountry(Country country) {
        List<TaxRate> taxRates = taxRateService.getTaxRateByCountry(country);
        return taxRates.stream()
                .collect(Collectors.toMap(
                        rate -> rate.getIncomeCategory().getId(),
                        TaxRate::getRate
                ));
    }

    private List<IncomeDetailResponceDto> calculateIncomeDetails(User user, Map<Long, BigDecimal> taxRateMap) {
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

    private CountryReportResponceDto buildCountryReport(Country country, List<IncomeDetailResponceDto> incomeDetails) {
        BigDecimal totalIncome = incomeDetails.stream()
                .map(IncomeDetailResponceDto::income)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalTax = incomeDetails.stream()
                .map(IncomeDetailResponceDto::tax)
                .reduce(BigDecimal.ZERO, BigDecimal::add);


        return new CountryReportResponceDto(
                countryService.mapModelToResponceDto(country),
                totalIncome,
                totalTax,
                incomeDetails
        );
    }
}
