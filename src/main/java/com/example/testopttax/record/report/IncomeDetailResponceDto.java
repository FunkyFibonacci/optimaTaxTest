package com.example.testopttax.record.report;

import com.example.testopttax.record.incomeCategory.IncomeCategoryResponceDto;

import java.math.BigDecimal;

public record IncomeDetailResponceDto(
        IncomeCategoryResponceDto incomeCategory,
        BigDecimal income,
        BigDecimal tax
) {
}
