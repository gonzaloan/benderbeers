package tech.nullpointerexception.bender.service;

import tech.nullpointerexception.bender.dto.BeerDto;

import java.util.List;

public interface BeerService {
    List<BeerDto> getAllBeers();

    BeerDto getBeerById(Integer beerId);

    BeerDto createBeer(BeerDto beerDto);

}
