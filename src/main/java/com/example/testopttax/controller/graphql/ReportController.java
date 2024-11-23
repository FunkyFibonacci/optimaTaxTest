package com.example.testopttax.controller.graphql;

import com.example.testopttax.record.report.CountryReportResponceDto;
import com.example.testopttax.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class ReportController {
    private final ReportService reportService;
    @QueryMapping
    public CountryReportResponceDto generateReportByCountryId(@Argument Long countryId){
        return reportService.makeReportByCountryId(countryId);
    }
}
