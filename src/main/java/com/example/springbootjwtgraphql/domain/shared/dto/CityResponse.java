package com.example.springbootjwtgraphql.domain.shared.dto;

import com.example.springbootjwtgraphql.domain.entities.City;

public class CityResponse {
    private String name;
    private String code;
    private String country;

    public CityResponse(City city) {
        this.name = city.getName();
        this.code = city.getCode();
    }

    public void setCountry(String country) {
        this.country = country;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public String getCountry() {
        return country;
    }
}