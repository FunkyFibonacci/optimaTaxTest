package com.example.testopttax.service;

import com.example.testopttax.record.country.CountryInput;
import com.example.testopttax.record.country.CountryResponceDto;
import org.springframework.stereotype.Service;

@Service
public interface CountryService {
    CountryResponceDto addCountry(CountryInput input);
}
