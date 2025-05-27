package com.example.testopttax.record.mlDtos;

import java.time.LocalTime;

public record TransactionData(
        Float amount,
        String type,
        Float balance,
        Integer age,
        LocalTime time,
        Boolean fraud
) {
}
