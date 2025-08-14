package com.example.springbootjwtgraphql.application.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.springbootjwtgraphql.domain.entities.City;
import com.example.springbootjwtgraphql.domain.entities.Country;
import com.example.springbootjwtgraphql.domain.repositories.CityRepository;
import com.example.springbootjwtgraphql.domain.repositories.CountryRepository;
import com.example.springbootjwtgraphql.domain.shared.dto.CityRequest;
import com.example.springbootjwtgraphql.domain.shared.dto.CityResponse;

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

    public List<City> getCities() {
        return cityRepository.findAll(Sort.by(Sort.Order.asc("id")));
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

    public CityResponse saveCity(CityRequest cityRequest) {
        Country country = countryRepository.findById(cityRequest.countryId);

        City city = new City();
        city.setName(cityRequest.name);
        city.setCode(cityRequest.code);
        city.setCountryId(country.getId());

        City savedCity = cityRepository.save(city);

        CityResponse response = new CityResponse(savedCity);
        response.setCountry(country.getName());
        return response;
    }

    public int getCount() {
        return cityRepository.getCountProcedure();
    }
}
