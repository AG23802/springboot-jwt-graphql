package com.example.springbootjwtgraphql.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "countries")
public class Country extends Place {
    @JsonIgnore
    @OneToMany
//    @AttributeOverride is used in JPA to override column mappings of inherited fields from a superclass in a subclass entity.@AttributeOverride is used in JPA to override column mappings of inherited fields from a superclass in a subclass entity.
//    @AttributeOverride(name = "id", column = @Column(name = "city_id"))
    @JoinColumn(name = "country_id")
    private Set<City> cities;

    public Set<City> getCities() {
        return cities;
    }

    public void setCities(Set<City> cities) {
        this.cities = cities;
    }
}
