package com.example.testopttax.record.taxRate;

import jakarta.validation.constraints.NotNull;

public record TaxRateInput(
        @NotNull
        Long countryId,
        @NotNull
        Long incomeCategoryId,
        Float rate
) {
}
