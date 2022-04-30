package com.fleetms.settings.repositories;

import com.fleetms.settings.model.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StateRepository extends JpaRepository<State, Integer> {

    @Query(value = "select s from State s where" +
            " concat(s.name, s.capital, s.code, s.details) LIKE %?1%")
    List<State> findByKeyword(String keyword);
}
