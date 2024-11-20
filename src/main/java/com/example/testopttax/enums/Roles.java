package com.example.testopttax.enums;

import lombok.Getter;

@Getter
public enum Roles {
    USER("USER");
    private final String value;
    Roles(String value) {
        this.value = value;
    }
}
