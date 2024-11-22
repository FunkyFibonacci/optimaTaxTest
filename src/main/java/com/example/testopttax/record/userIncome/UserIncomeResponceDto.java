package com.example.testopttax.record.userIncome;

import com.example.testopttax.record.UserResponceDto;
import com.example.testopttax.record.incomeCategory.IncomeCategoryResponceDto;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record UserIncomeResponceDto(
        Long id,
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        LocalDateTime createdAt,
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        LocalDateTime updatedAt,
        UserResponceDto user,
        IncomeCategoryResponceDto incomeCategory,
        BigDecimal amount

) {
}
