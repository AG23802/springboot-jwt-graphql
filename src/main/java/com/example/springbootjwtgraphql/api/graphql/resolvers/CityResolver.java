package com.example.springbootjwtgraphql.api.graphql.resolvers;

import com.example.springbootjwtgraphql.domain.entities.City;
import com.example.springbootjwtgraphql.domain.entities.Country;
import com.example.springbootjwtgraphql.domain.repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.Optional;

@Controller
public class CityResolver {

    @Autowired
    private CountryRepository countryRepository;

    // This maps the "country" field in the City type
    @SchemaMapping(typeName = "City", field = "country")
    public String getCountry(City city) {
        // Using your repository to fetch the country by countryId
        Optional<Country> country = countryRepository.findById((long) city.getCountryId());
        return country.map(Country::getName).orElse(null);
    }
}