package com.fleetms.settings.repositories;

import com.fleetms.settings.model.Country;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountryRepository extends JpaRepository<Country, Integer> {

    @Query(value = "select c from Country c where" +
            " concat(c.description, c.code, c.capital, c.nationality, c.continent) LIKE %?1%")
    List<Country> findByKeyword(String keyword);

}
