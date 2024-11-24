package com.example.testopttax.service.impl;

import com.example.testopttax.enums.ReportFormat;
import com.example.testopttax.exception.CustomException;
import com.example.testopttax.model.Country;
import com.example.testopttax.record.country.CountryInput;
import com.example.testopttax.record.country.CountryResponceDto;
import com.example.testopttax.repo.CountryRepository;
import com.example.testopttax.repo.UserRepository;
import com.example.testopttax.service.CountryService;
import graphql.GraphQLException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Component
@Slf4j
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryService {
    private final CountryRepository countryRepository;

    @Override
    public CountryResponceDto addCountry(CountryInput input) {
        if (input.code().length()<3 || input.code().isBlank()){
            log.error("Код страные иммеет некорректные значения!");
            throw new CustomException("Код страны должен быть равен 3 символам!");
        }

        if (countryRepository.findByNameIgnoreCase(input.name()).isPresent() || countryRepository.findByCodeIgnoreCase(input.code()).isPresent()){
            log.error("Страна с таким названием или кодом уже существует!");
            throw new CustomException("Страна с таким названием или кодом уже существует!");
        }
        ReportFormat reportFormat;
        try {
            reportFormat = ReportFormat.valueOf(input.reportFormat());
        } catch (IllegalArgumentException e) {
            log.error("Некорректный формат отчета: {}", input.reportFormat());
            throw new CustomException("Некорректный формат отчета, узнайте сначало форматы отчетов!");
        }

        Country country = new Country();
        country.setCode(input.code().toUpperCase());
        country.setName(input.name());
        country.setReportFormat(reportFormat);
        Country countrySaved = countryRepository.save(country);
        log.info("Успешно создана новая страна: {}", countrySaved.getName());
        return mapModelToResponceDto(countrySaved);
    }

    @Override
    public List<CountryResponceDto> getCountries() {
        return countryRepository.findAll().stream().map(country -> new CountryResponceDto(
                country.getId(),
                country.getCreatedAt(),
                country.getUpdatedAt(),
                country.getName(),
                country.getCode(),
                country.getReportFormat().getValue()
                )
        ).collect(Collectors.toList());
    }

    @Override
    public CountryResponceDto getCountryById(Long id) {
        Country countryFromDb = findById(id);
        return mapModelToResponceDto(countryFromDb);
    }

    @Override
    public CountryResponceDto getCountryByName(String name) {
        Country countryFromDb = countryRepository.findByNameIgnoreCase(name)
                .orElseThrow(() -> {
                    log.error("Не найдена страна с именем: {}", name);
                    return new CustomException("Не найдена страна по названию!");
                });
        return mapModelToResponceDto(countryFromDb);
    }

    @Override
    public CountryResponceDto mapModelToResponceDto(Country country){
        return new CountryResponceDto(country.getId(), country.getCreatedAt(), country.getUpdatedAt(), country.getName(),country.getCode(),country.getReportFormat().getValue());
    }

    @Override
    public Country findById(Long id) {
        return countryRepository.findById(id).orElseThrow(() -> {
            log.error("Не найдена страна с id: {}", id);
            return new CustomException("Не найдена страна с id:"+id);
        });
    }
}
