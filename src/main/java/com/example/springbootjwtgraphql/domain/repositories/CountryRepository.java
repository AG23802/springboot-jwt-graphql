package com.example.springbootjwtgraphql.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.springbootjwtgraphql.domain.entities.Country;

import java.util.Optional;

@Repository
public interface CountryRepository extends JpaRepository<Country, Integer> {
    Country findById(int id);

    Optional<Country> findById(Long id);
}
