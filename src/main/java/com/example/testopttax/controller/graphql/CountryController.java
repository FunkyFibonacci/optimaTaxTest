package com.example.testopttax.controller.graphql;

import com.example.testopttax.record.country.CountryInput;
import com.example.testopttax.record.country.CountryResponceDto;
import com.example.testopttax.service.CountryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class CountryController {
    private final CountryService countryService;

    @MutationMapping
    public CountryResponceDto addCountry(@Argument("input") CountryInput input){
        return countryService.addCountry(input);
    }

}
