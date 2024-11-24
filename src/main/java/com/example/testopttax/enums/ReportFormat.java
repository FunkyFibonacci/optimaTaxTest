package com.example.testopttax.enums;

import lombok.Getter;

@Getter
public enum ReportFormat {
    DETAILED("DETAILED"),
    SUMMARY("SUMMARY"),
    CATEGORY_WITH_SIGNATURE("CATEGORY_WITH_SIGNATURE");

    private final String value;

    ReportFormat(String value) {
        this.value = value;
    }
    public static boolean isValid(String value) {
        for (ReportFormat format : values()) {
            if (format.getValue().equalsIgnoreCase(value)) {
                return true;
            }
        }
        return false;
    }
}
