package com.example.testopttax.record.userIncome;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record UserIncomeInput(
        @NotNull
        Long incomeCategoryId,
        Float amount
) {
}
