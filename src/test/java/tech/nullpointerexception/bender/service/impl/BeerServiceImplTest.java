package tech.nullpointerexception.bender.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import tech.nullpointerexception.bender.dto.BeerDto;
import tech.nullpointerexception.bender.dto.BeerPriceDto;
import tech.nullpointerexception.bender.exception.BeerException;
import tech.nullpointerexception.bender.model.Beer;
import tech.nullpointerexception.bender.repository.BeerRepository;
import tech.nullpointerexception.bender.utils.TestConstants;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;

@ExtendWith(MockitoExtension.class)
class BeerServiceImplTest {

    @Mock
    private BeerRepository beerRepository;



    @InjectMocks
    private BeerServiceImpl beerService;


    @Test
    void getAllBeers() {
        List<Beer> repositoryResult = TestConstants.GET_BEER_LIST().stream()
                .map(beer-> new Beer(beer.getId(),
                        beer.getName(),
                        beer.getBrewery(),
                        beer.getCountry(),
                        beer.getPrice(),
                        beer.getCurrency()
                ))
                .collect(Collectors.toList());

        Mockito.when(beerRepository.findAll())
                .thenReturn(repositoryResult);

        List<BeerDto> allBeers = beerService.getAllBeers();

        assertThat(allBeers).isNotNull()
                .isNotEmpty();
    }

    @Test
    void getAllBeers_returns_no_object() {
        Mockito.when(beerRepository.findAll())
                .thenReturn(Collections.emptyList());

        List<BeerDto> allBeers = beerService.getAllBeers();

        assertThat(allBeers).isEmpty();
    }

    @Test
    void getBeerById() {

        Mockito.when(beerRepository.findById(anyInt()))
                .thenReturn(Optional.of(TestConstants.GET_NEW_BEER_MODEL()));

        BeerDto beerById = beerService.getBeerById(1);

        assertThat(beerById).isNotNull();
        assertThat(beerById.getName()).isEqualTo(TestConstants.GET_NEW_BEER().getName());

    }

    @Test
    void createBeer() {

        //Si no existe se debe crear
        Mockito.when(beerRepository.existsById(anyInt())).thenReturn(Boolean.FALSE);

        Mockito.when(beerRepository.save(any()))
                .thenReturn(TestConstants.GET_NEW_BEER_MODEL());

        BeerDto beer = beerService.createBeer(TestConstants.GET_NEW_BEER());

        assertThat(beer).isNotNull();
        assertThat(beer.getName()).isEqualTo(TestConstants.GET_NEW_BEER().getName());
        assertThat(beer.getBrewery()).isEqualTo(TestConstants.GET_NEW_BEER().getBrewery());


    }

    @Test
    void createBeer_when_id_exists() {
        //Si no existe se debe crear
        Mockito.when(beerRepository.existsById(anyInt())).thenReturn(Boolean.TRUE);
        Assertions.assertThrows(BeerException.class, () -> {
            BeerDto beer = beerService.createBeer(TestConstants.GET_NEW_BEER());
        });
    }

}