package com.digitalinnovation.beer_api.service;


import com.digitalinnovation.beer_api.builder.BeerDTOBuilder;
import com.digitalinnovation.beer_api.dto.BeerDTO;
import com.digitalinnovation.beer_api.entity.Beer;
import com.digitalinnovation.beer_api.exception.BeerAlreadyRegisteredException;
import com.digitalinnovation.beer_api.exception.BeerNotFoundException;
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
import static org.mockito.Mockito.*;

import java.util.Collections;
import java.util.List;
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
    BeerDTO expectedBeerDTO = BeerDTOBuilder.builder().build().toBeerDTO();
    Beer expectedSaveBeer = beerMapper.toModel(expectedBeerDTO);

    when(beerRepository.findByName(expectedBeerDTO.getName())).thenReturn(Optional.empty());
    when(beerRepository.save(expectedSaveBeer)).thenReturn(expectedSaveBeer);

    BeerDTO createdBeerDTO = beerService.createBeer(expectedBeerDTO);

    assertThat(createdBeerDTO.getId(), is(equalTo(expectedBeerDTO.getId())));
    assertThat(createdBeerDTO.getName(), is(equalTo(expectedBeerDTO.getName())));
    assertThat(createdBeerDTO.getQuantity(), is(equalTo(expectedBeerDTO.getQuantity())));

  }

  @Test
  void whenAlreadyRegisteredBeerInformedThenAnExceptionShouldBeThrow() throws BeerAlreadyRegisteredException {

    BeerDTO expectedBeerDTO = BeerDTOBuilder.builder().build().toBeerDTO();
    Beer duplicatedBeer = beerMapper.toModel(expectedBeerDTO);

    when(beerRepository.findByName(expectedBeerDTO.getName())).thenReturn(Optional.of(duplicatedBeer));

    assertThrows(BeerAlreadyRegisteredException.class, () -> beerService.createBeer(expectedBeerDTO));
  }

  @Test
  void whenValidBeerNameIsGivenThenReturnABeer() throws BeerNotFoundException {
    BeerDTO expectedFoundBeerDTO = BeerDTOBuilder.builder().build().toBeerDTO();
    Beer expectedFoundBeer = beerMapper.toModel(expectedFoundBeerDTO);

    when(beerRepository.findByName(expectedFoundBeer.getName())).thenReturn(Optional.of(expectedFoundBeer));

    BeerDTO foundBeerDTO = beerService.findByName(expectedFoundBeerDTO.getName());

    assertThat(foundBeerDTO, is(equalTo(expectedFoundBeerDTO)));
  }

  @Test
  void whenNotRegisteredBeerNameIsGivenThenThrowAnExeption() throws BeerNotFoundException {
    BeerDTO expectedFoundBeerDTO = BeerDTOBuilder.builder().build().toBeerDTO();

    when(beerRepository.findByName(expectedFoundBeerDTO.getName())).thenReturn(Optional.empty());

    assertThrows(BeerNotFoundException.class, () -> beerService.findByName(expectedFoundBeerDTO.getName()));
  }

  @Test
  void whenListBeerIsCalledThenReturnAListOfBeers() {
    BeerDTO expectedFoundBeerDTO = BeerDTOBuilder.builder().build().toBeerDTO();
    Beer expectedFoundBeer = beerMapper.toModel(expectedFoundBeerDTO);

    when(beerRepository.findAll()).thenReturn(Collections.singletonList(expectedFoundBeer));

    List<BeerDTO> foundListBeersDTO = beerService.listAll();

    assertThat(foundListBeersDTO, is(not(empty())));
    assertThat(foundListBeersDTO.get(0), is(equalTo(expectedFoundBeerDTO)));
  }

  @Test
  void whenListBeerIsCalledThenReturnAnEmptyListOfBeers() {
    when(beerRepository.findAll()).thenReturn(Collections.EMPTY_LIST);

    List<BeerDTO> foundListBeersDTO = beerService.listAll();

    assertThat(foundListBeersDTO, is(empty()));
  }

}
