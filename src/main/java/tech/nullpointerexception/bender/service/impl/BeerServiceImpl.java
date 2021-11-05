package tech.nullpointerexception.bender.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import tech.nullpointerexception.bender.dto.BeerDto;
import tech.nullpointerexception.bender.dto.BeerPriceDto;
import tech.nullpointerexception.bender.exception.BeerException;
import tech.nullpointerexception.bender.exception.NotFoundException;
import tech.nullpointerexception.bender.mappers.BeerMapper;
import tech.nullpointerexception.bender.repository.BeerRepository;
import tech.nullpointerexception.bender.service.BeerService;
import tech.nullpointerexception.bender.service.CurrencyService;
import tech.nullpointerexception.bender.util.UtilConstants;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Scope("prototype")
@RequiredArgsConstructor
@Slf4j
public class BeerServiceImpl implements BeerService {

    private final BeerRepository beerRepository;
    private final CurrencyService currencyService;

    @Override
    public List<BeerDto> getAllBeers() {
        return Optional.of(beerRepository.findAll()
                .stream()
                .map(BeerMapper.INSTANCE::beerToBeerDto)
                .collect(Collectors.toList()))
                .orElse(null);
    }

    @Override
    public BeerDto getBeerById(Integer beerId) {
        return beerRepository.findById(beerId)
                .map(BeerMapper.INSTANCE::beerToBeerDto)
                .orElse(null);

    }

    @Override
    public BeerDto createBeer(BeerDto beerDto) {
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
        BeerDto beerById = Optional.ofNullable(this.getBeerById(beerId)).orElseThrow(NotFoundException::new);

        return Optional.of(beerById)
                .map(x -> currencyService.convertBetweenCurrency(x.getCurrency(), currency, x.getPrice()))
                .map(x -> quantity * x)
                .map(x -> BeerPriceDto.builder().totalPrice(new BigDecimal(x).setScale(2, RoundingMode.HALF_UP).doubleValue()).build())
                .orElseThrow(() -> new BeerException(UtilConstants.CURRENCY_IS_INVALID));
    }

}
