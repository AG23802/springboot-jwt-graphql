package com.example.springbootjwtgraphql.api.graphql.controllers;

import com.example.springbootjwtgraphql.application.services.CityService;
import com.example.springbootjwtgraphql.domain.entities.City;
import com.example.springbootjwtgraphql.domain.shared.dto.CityRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class CityController {

    @Autowired
    private CityService cityService;

    @QueryMapping
public List<City> cities(@Argument Integer page,
                         @Argument Integer size,
                         @Argument String nameFilter) {

    return cityService.getCities(page, size, nameFilter);
}

    @QueryMapping(name = "cityByCode")
    public City getCity(@Argument("code") String code) {
        return cityService.getCity(code);
    }

    @MutationMapping
    public City createCity(@Argument CityRequest cityRequest) {
        return cityService.saveCity(cityRequest);
    }
}