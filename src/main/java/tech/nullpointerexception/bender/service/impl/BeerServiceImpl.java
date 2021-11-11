package tech.nullpointerexception.bender.service.impl;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import tech.nullpointerexception.bender.dto.BeerDto;
import tech.nullpointerexception.bender.exception.BeerException;
import tech.nullpointerexception.bender.model.Beer;
import tech.nullpointerexception.bender.repository.BeerRepository;
import tech.nullpointerexception.bender.service.BeerService;
import tech.nullpointerexception.bender.util.UtilConstants;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BeerServiceImpl implements BeerService {

    private final BeerRepository beerRepository;

    public BeerServiceImpl(BeerRepository beerRepository) {
        this.beerRepository = beerRepository;
    }

    @Override
    public List<BeerDto> getAllBeers() {
        return Optional.of(beerRepository.findAll()
                        .stream()
                        .map(beer-> new BeerDto(beer.getId(),
                                beer.getName(),
                                beer.getBrewery(),
                                beer.getCountry(),
                                beer.getPrice(),
                                beer.getCurrency()
                                ))
                .collect(Collectors.toList()))
                .orElse(null);
    }

    @Override
    public BeerDto getBeerById(Integer beerId) {
        return beerRepository.findById(beerId)
                .map(beer-> new BeerDto(beer.getId(),
                        beer.getName(),
                        beer.getBrewery(),
                        beer.getCountry(),
                        beer.getPrice(),
                        beer.getCurrency()
                ))
                .orElse(null);

    }

    @Override
    public BeerDto createBeer(BeerDto beerDto) {
        if (Objects.nonNull(beerDto.getId()) && beerRepository.existsById(beerDto.getId())) {
            throw new BeerException(UtilConstants.CANT_REPEAT_BEER_ID);
        }

        Beer beer = new Beer(beerDto.getName(),
                beerDto.getBrewery(),
                beerDto.getCountry(),
                beerDto.getPrice(),
                beerDto.getCurrency());
        beerRepository.save(beer);

        beerDto.setId(beer.getId());
        return beerDto;
    }
}
