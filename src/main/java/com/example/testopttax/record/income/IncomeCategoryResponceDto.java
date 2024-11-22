package com.example.testopttax.record.income;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record IncomeCategoryResponceDto(
        Long id,
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        LocalDateTime createdAt,
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        LocalDateTime updatedAt,
        String name
) {
}
