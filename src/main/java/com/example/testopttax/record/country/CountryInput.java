package com.example.testopttax.record.country;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record CountryInput(
        @Pattern(regexp = "^[A-Za-zА-Яа-я\\s]+$", message = "Название страны не должно содержать цифры!")
        String name,
        @Size(min = 3, max = 3, message = "Код страны должен быть статичным из 3-х цифр!")
        @Pattern(regexp = "^[A-Za-zА-Яа-я\\s]+$", message = "Код код должен содержать только буквенные значения")
        String code,
        @NotBlank(message = "Формат отчета не может быть пустым!")
        String reportFormat

) {
}
