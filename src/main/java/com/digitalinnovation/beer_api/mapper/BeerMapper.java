package com.digitalinnovation.beer_api.mapper;

import com.digitalinnovation.beer_api.dto.BeerDTO;
import com.digitalinnovation.beer_api.entity.Beer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BeerMapper {

    BeerMapper INSTANCE = Mappers.getMapper(BeerMapper.class);

    Beer toModel(BeerDTO beerDTO);

    BeerDTO toDTO(Beer beer);
}
