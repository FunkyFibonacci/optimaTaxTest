package com.example.testopttax.service;

import com.example.testopttax.record.report.CountryReportResponceDto;
import org.springframework.stereotype.Service;

@Service
public interface ReportService {
    CountryReportResponceDto makeReportByCountryId(Long countryId);
}
