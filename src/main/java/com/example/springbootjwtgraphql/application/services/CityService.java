package com.example.springbootjwtgraphql.application.services;

import com.example.springbootjwtgraphql.commons.CityRequest;
import com.example.springbootjwtgraphql.domain.entities.City;
import com.example.springbootjwtgraphql.domain.entities.Country;
import com.example.springbootjwtgraphql.domain.repositories.CountryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.springbootjwtgraphql.domain.repositories.CityRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Service
public class CityService {
    @Autowired
    CityRepository cityRepository;

    @Autowired
    CountryRepository countryRepository;

    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public Page<City> getCities(int page, int size, String nameFilter) {
        Pageable pageable = PageRequest.of(page, size);

        if (nameFilter != null && !nameFilter.isEmpty()) {
            return cityRepository.findByNameContainingIgnoreCase(nameFilter, pageable);
        } else {
            return cityRepository.findAll(pageable);
        }
    }

    public List<City> findCities(int countryId, Long population) {
        return cityRepository.findByCountryIdAndPopulationLessThan(countryId, population);
    }

    public City getCity(String cityCode) {
        return cityRepository.findByCode(cityCode);
    }

    public List<City> getCityByCode(String cityCode) {
        return cityRepository.getByCode(cityCode);
    }

    public City saveCity(CityRequest cityRequest) {
        // Fetch the country entity
        Country country = countryRepository.findById((long) cityRequest.countryId)
                .orElseThrow(() -> new RuntimeException("Country not found"));

        // Create new city entity
        City city = new City();
        city.setName(cityRequest.name);
        city.setCode(cityRequest.code);
        city.setCountryId(country.getId());

        // Save and return the city
        return cityRepository.save(city);
    }

    public int getCount() {
        return cityRepository.getCountProcedure();
    }
}
