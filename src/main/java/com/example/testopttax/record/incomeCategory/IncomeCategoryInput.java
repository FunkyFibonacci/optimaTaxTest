package com.example.testopttax.record.incomeCategory;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record IncomeCategoryInput(
        @NotBlank(message = "Категория не должна быть пустым!")
        String name
) {
}
