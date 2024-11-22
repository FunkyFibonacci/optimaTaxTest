package com.example.testopttax.service.impl;

import com.example.testopttax.exception.CustomException;
import com.example.testopttax.model.Country;
import com.example.testopttax.record.country.CountryInput;
import com.example.testopttax.record.country.CountryResponceDto;
import com.example.testopttax.repo.CountryRepository;
import com.example.testopttax.service.CountryService;
import graphql.GraphQLException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

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
        Country country = new Country();
        country.setCode(input.code());
        country.setName(input.name());
        Country countrySaved = countryRepository.save(country);
        log.info("Успешно создана новая страна: {}", countrySaved.getName());
        return new CountryResponceDto(countrySaved.getId(), countrySaved.getCreatedAt(),countrySaved.getUpdatedAt(),countrySaved.getName(),countrySaved.getCode());
    }
}
