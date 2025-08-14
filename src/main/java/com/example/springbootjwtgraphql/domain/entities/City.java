package com.example.springbootjwtgraphql.domain.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "cities")
public class City extends Place {
    @Column(name = "code")
    private String code;

    @Column(name = "country_id")
    private int countryId;

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
}