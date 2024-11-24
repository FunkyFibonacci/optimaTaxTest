package com.example.testopttax.record;

import io.swagger.v3.oas.annotations.media.Schema;


@Schema(description = "Объект для передачи данных пользователя")
public record UserDto(

        @Schema(description = "Имя пользователя", example = "Иван")
        String firstname,

        @Schema(description = "Фамилия пользователя", example = "Иванов")
        String lastname,

        @Schema(description = "Уникальное имя пользователя (логин)", example = "ivan_ivanov")
        String username,


        @Schema(description = "Пароль пользователя", example = "password123")
        String password,

        @Schema(description = "Роль пользователя (например, ADMIN, USER)", example = "USER")
        String role
) {
}
