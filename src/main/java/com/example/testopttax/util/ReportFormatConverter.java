package com.example.testopttax.util;

import com.example.testopttax.enums.ReportFormat;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class ReportFormatConverter implements AttributeConverter<ReportFormat, String> {

    @Override
    public String convertToDatabaseColumn(ReportFormat attribute) {
        if (attribute == null) {
            return null;
        }
        return attribute.getValue();
    }

    @Override
    public ReportFormat convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }
        return ReportFormat.valueOf(dbData);
    }
}