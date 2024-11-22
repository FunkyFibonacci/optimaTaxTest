package com.example.testopttax.service;

import com.example.testopttax.record.country.CountryInput;
import com.example.testopttax.record.country.CountryResponceDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CountryService {
    CountryResponceDto addCountry(CountryInput input);

    List<CountryResponceDto> getCountries();

    CountryResponceDto getCountryById(Long id);

    CountryResponceDto getCountryByName(String name);
}
