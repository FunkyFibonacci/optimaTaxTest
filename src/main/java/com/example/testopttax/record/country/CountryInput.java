package com.example.testopttax.record.country;

import jakarta.validation.constraints.Size;

public record CountryInput(String name,
                           @Size(min = 3, max = 3, message = "Code must be exactly 3 characters long")
                           String code) {
}
