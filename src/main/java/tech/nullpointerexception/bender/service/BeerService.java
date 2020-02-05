package tech.nullpointerexception.bender.service;

import tech.nullpointerexception.bender.dto.BeerDto;
import tech.nullpointerexception.bender.dto.BeerPriceDto;

import java.util.List;

public interface BeerService {
    List<BeerDto> getAllBeers();

    BeerDto getBeerById(Integer beerId);

    BeerDto createBeer(BeerDto beerDto);

    BeerPriceDto getBeerListedPriceByCurrencyAndQuantity(Integer beerId, String currency, Integer quantity);
}
