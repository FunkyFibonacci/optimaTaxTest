package com.example.testopttax.record;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record RoleDto (Long id,
                   @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
                   LocalDateTime createdAt,
                   @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
                   LocalDateTime updatedAt,
                   String role) {
}
