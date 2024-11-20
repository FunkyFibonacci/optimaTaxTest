package com.example.testopttax.record;

import java.time.LocalDateTime;

public record UserResponceDto(
        Long id,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        String firstname,
        String lastname,
        String username
) {
}
