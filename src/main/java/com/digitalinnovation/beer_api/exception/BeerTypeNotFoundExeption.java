package com.digitalinnovation.beer_api.exception;

import com.digitalinnovation.beer_api.enums.BeerType;

public class BeerTypeNotFoundExeption extends Exception{

  public BeerTypeNotFoundExeption(BeerType type) {
    super(String.format("Beer with type %s not found in the system.", type));
  }

}