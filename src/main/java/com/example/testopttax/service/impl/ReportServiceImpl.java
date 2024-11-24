package com.example.testopttax.service.impl;

import com.example.testopttax.enums.ReportFormat;
import com.example.testopttax.exception.CustomException;
import com.example.testopttax.model.Country;
import com.example.testopttax.model.User;
import com.example.testopttax.record.report.CountryReportResponceDto;
import com.example.testopttax.service.*;
import com.example.testopttax.service.strategy.ReportFormatStrategy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


import java.util.Map;
import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final UserService userService;
    private final CountryService countryService;
    private final Map<ReportFormat, ReportFormatStrategy> reportFormatStrategies;


    @Override
    public CountryReportResponceDto makeReportByCountryId(Long countryId) {
        if (countryId == null) {
            log.error("ID страны не может быть null");
            throw new CustomException("ID страны не указан!");
        }
        User user = userService.getUserBySecurityHolder(SecurityContextHolder.getContext().getAuthentication());
        Country country = countryService.findById(countryId);

        ReportFormat reportFormat = country.getReportFormat();
        ReportFormatStrategy strategy = reportFormatStrategies.get(reportFormat);

        if (Objects.nonNull(strategy)){
            return strategy.getReport(user, country);
        } else {
            log.error("Не существует тип отчета для страны!");
            throw new CustomException("Устраны нет типа отчета!");
        }
    }

}
