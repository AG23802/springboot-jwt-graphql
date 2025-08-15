package com.example.springbootjwtgraphql.domain.repositories;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import com.example.springbootjwtgraphql.domain.entities.City;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
    // JPA
    City findByCode(String cityCode);

    // JPQL
    @Query("from City where code = :code")
    List<City> getByCode(@Param("code") String cityCode);

    // NATIVE SQL QUERY
    @Query(value = "SELECT * FROM cities WHERE country_id = :countryId AND population < :population", nativeQuery = true)
    List<City> findByCountryIdAndPopulationLessThan(@Param("countryId") int countryId, @Param("population") Long population);

//    List<City> findByCountryIdAndPopulationLessThan(int countryId, Long population);

    @Query(value = "SELECT count_cities()", nativeQuery = true)
    int getCountNQuery();

    @Procedure(procedureName = "count_cities_proc")
    int getCountProcedure();

    Page<City> findByNameContainingIgnoreCase(String name, Pageable pageable);
}
