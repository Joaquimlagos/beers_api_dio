package com.digitalinnovation.beer_api.exception;

import com.digitalinnovation.beer_api.enums.BeerType;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BeerNotFoundException extends Exception {

    public BeerNotFoundException(String beerName) {
        super(String.format("Beer with name %s not found in the system.", beerName));
    }

   /* public BeerNotFoundException(BeerType type) {
        super(String.format("Beer with type %s not found in the system.", type));
    } */

    public BeerNotFoundException(Long id) {
        super(String.format("Beer with id %s not found in the system.", id));
    }
}
