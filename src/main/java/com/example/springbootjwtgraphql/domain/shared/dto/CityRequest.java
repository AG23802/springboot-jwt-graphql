package com.example.springbootjwtgraphql.domain.shared.dto;

public class CityRequest {
    public String name;
    public String code;
    public int countryId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    // Custom toString method for better debugging
    @Override
    public String toString() {
        return "CityRequest{name='" + name + "', code='" + code + "', countryId=" + countryId + "}";
    }
}
