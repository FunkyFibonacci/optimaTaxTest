package com.example.testopttax.record.taxRate;

public record TaxRateInput(
        Long countryId,
        Long incomeCategoryId,
        Float rate
) {
}
