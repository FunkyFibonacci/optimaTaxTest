package com.example.testopttax;

import com.example.testopttax.enums.ReportFormat;
import com.example.testopttax.exception.CustomException;

import com.example.testopttax.service.CountryService;
import com.example.testopttax.service.UserService;
import com.example.testopttax.service.impl.ReportServiceImpl;
import com.example.testopttax.service.strategy.ReportFormatStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verifyNoInteractions;

public class ReportServiceImplTest {
    @Mock
    private UserService userService;

    @Mock
    private CountryService countryService;

    @Mock
    private Map<ReportFormat, ReportFormatStrategy> reportFormatStrategies;


    @InjectMocks
    private ReportServiceImpl reportService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void makeReportByCountryId_ShouldThrowException_WhenCountryIdIsNull() {
        CustomException exception = assertThrows(CustomException.class, () -> reportService.makeReportByCountryId(null));
        assertEquals("ID страны не указан!".toLowerCase(), exception.getMessage().toLowerCase());
        verifyNoInteractions(userService, countryService, reportFormatStrategies);
    }


}
