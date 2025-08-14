package com.example.springbootjwtgraphql.domain.shared.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class CityRequest {
    @NotNull(message = "Name cannot be null.")
    public String name;

    @Pattern(regexp = "^[A-Z]+$", message = "Code must contain only uppercase letters.")
    @NotNull(message = "Code cannot be null.")
    @Size(min = 1, max = 2, message = "Code must be up to two characters long.")
    public String code;

    @NotNull(message = "Country Id cannot be null.")
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
