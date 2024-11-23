package com.example.testopttax.record.report;

import com.example.testopttax.record.country.CountryResponceDto;

import java.math.BigDecimal;
import java.util.List;

public record CountryReportResponceDto(
        CountryResponceDto country,
        BigDecimal totalIncome,
        BigDecimal totalTax,
        List<IncomeDetailResponceDto> incomeDetail,
        String signature) {
}
