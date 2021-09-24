package com.digitalinnovation.beer_api.service;


import com.digitalinnovation.beer_api.builder.BeerDTOBuilder;
import com.digitalinnovation.beer_api.dto.BeerDTO;
import com.digitalinnovation.beer_api.entity.Beer;
import com.digitalinnovation.beer_api.exception.BeerAlreadyRegisteredException;
import com.digitalinnovation.beer_api.mapper.BeerMapper;
import com.digitalinnovation.beer_api.repository.BeerRepository;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;
import java.util.regex.Matcher;

@ExtendWith(MockitoExtension.class)
public class BeerServiceTest {

  private static final long INVALID_BEER_ID = 11;

  @Mock
  private BeerRepository beerRepository;

  private BeerMapper beerMapper = BeerMapper.INSTANCE;

  @InjectMocks
  private BeerService beerService;

  @Test
  void whenBeerInformedThenShuldBeCreated() throws BeerAlreadyRegisteredException {
    BeerDTO beerDTO = BeerDTOBuilder.builder().build().toBeerDTO();
    Beer expectedSaveBeer = beerMapper.toModel(beerDTO);

    Mockito.when(beerRepository.findByName(beerDTO.getName())).thenReturn(Optional.empty());
    Mockito.when(beerRepository.save(expectedSaveBeer)).thenReturn(expectedSaveBeer);

    BeerDTO createdBeerDTO = beerService.createBeer(beerDTO);

    assertThat(createdBeerDTO.getId(), is(equalTo(beerDTO.getId())));
    assertThat(createdBeerDTO.getName(), is(equalTo(beerDTO.getName())));
    assertThat(createdBeerDTO.getQuantity(), is(equalTo(beerDTO.getQuantity())));

  }

}
