package tech.nullpointerexception.bender.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tech.nullpointerexception.bender.dto.BeerDto;
import tech.nullpointerexception.bender.dto.BeerPriceDto;
import tech.nullpointerexception.bender.exception.BeerException;
import tech.nullpointerexception.bender.mappers.BeerMapper;
import tech.nullpointerexception.bender.repository.BeerRepository;
import tech.nullpointerexception.bender.service.BeerService;
import tech.nullpointerexception.bender.util.UtilConstants;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class BeerServiceImpl implements BeerService {

    private final BeerRepository beerRepository;


    @Override
    public List<BeerDto> getAllBeers() {
        log.info("Dentro de Service de Get All Beers.");
        return Optional.of(beerRepository.findAll()
                .stream()
                .map(BeerMapper.INSTANCE::beerToBeerDto)
                .collect(Collectors.toList()))
                .orElse(null);
    }

    @Override
    public BeerDto getBeerById(Integer beerId) {
        log.info("Dentro de Service de Busqueda de Cerveza por ID {}", beerId);
        return beerRepository.findById(beerId)
                .map(BeerMapper.INSTANCE::beerToBeerDto)
                .orElse(null);

    }

    @Override
    public BeerDto createBeer(BeerDto beerDto) {
        log.info("Creacion de nueva Beer, createBeer en Servicio Impl: {}", beerDto.toString());
        if (beerRepository.existsById(beerDto.getId())) {
            throw new BeerException(UtilConstants.CANT_REPEAT_BEER_ID);
        }

        return Optional.of(beerDto)
                .map(BeerMapper.INSTANCE::beerDtoToBeer)
                .map(beerRepository::save)
                .map(BeerMapper.INSTANCE::beerToBeerDto)
                .orElse(null);
    }

    @Override
    public BeerPriceDto getBeerListedPriceByCurrencyAndQuantity(Integer beerId, String currency, Integer quantity) {
        log.info("Dentro de getBeerListedPriceByCurrencyAndQuantity");

            /*
            beerRepository.findById(beerId)
                    .map(BeerMapper.INSTANCE::beerToBeerDto)
                    .map(beerDto -> BeerPriceDto.builder().totalPrice(quantity * searchedCurrency.get))

             */
        return null;


    }

}
