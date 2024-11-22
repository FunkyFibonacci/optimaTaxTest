package com.example.testopttax.record.taxRate;

import com.example.testopttax.record.country.CountryResponceDto;
import com.example.testopttax.record.income.IncomeCategoryResponceDto;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TaxRateResponceDto(
        Long id,
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")

        LocalDateTime createdAt,
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        LocalDateTime updatedAt,
        CountryResponceDto country,
        IncomeCategoryResponceDto incomeCategory,
        BigDecimal rate
) {
}
