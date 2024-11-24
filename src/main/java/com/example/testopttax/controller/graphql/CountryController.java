package com.example.testopttax.controller.graphql;

import com.example.testopttax.record.country.CountryInput;
import com.example.testopttax.record.country.CountryResponceDto;
import com.example.testopttax.service.CountryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class CountryController {
    private final CountryService countryService;

    @MutationMapping
    public CountryResponceDto addCountry(@Argument("input") @Valid CountryInput input) {
        return countryService.addCountry(input);
    }
    @QueryMapping
    public CountryResponceDto getCountryById(@Argument Long id){
        return countryService.getCountryById(id);
    }

    @QueryMapping
    public CountryResponceDto getCountryByName(@Argument String name){
        return countryService.getCountryByName(name);
    }

    @QueryMapping
    public List<CountryResponceDto> getAllCountries() {
        return countryService.getCountries();
    }

}
