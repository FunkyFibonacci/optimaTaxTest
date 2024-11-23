package com.example.testopttax.service.strategy;

import com.example.testopttax.enums.ReportFormat;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class ConfigReportStrategy {
    @Bean
    public Map<ReportFormat, ReportFormatStrategy> reportFormatStrategies(
            DetailedReportFormat detailedReportFormat,
            SignatureReportFormat signatureReportFormat,
            SummaryReportFormat summaryReportFormat) {

        return Map.of(
                ReportFormat.DETAILED, detailedReportFormat,
                ReportFormat.CATEGORY_WITH_SIGNATURE, signatureReportFormat,
                ReportFormat.SUMMARY, summaryReportFormat
        );
    }
}