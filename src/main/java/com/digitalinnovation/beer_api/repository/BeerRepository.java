package com.digitalinnovation.beer_api.repository;

import com.digitalinnovation.beer_api.entity.Beer;
import com.digitalinnovation.beer_api.enums.BeerType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BeerRepository extends JpaRepository<Beer, Long> {

    Optional<Beer> findByName(String name);
    List<Beer> findByType(BeerType type);
}
