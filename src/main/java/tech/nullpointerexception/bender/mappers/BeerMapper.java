package tech.nullpointerexception.bender.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import tech.nullpointerexception.bender.dto.BeerDto;
import tech.nullpointerexception.bender.model.Beer;

@Mapper
public interface BeerMapper {

    BeerMapper INSTANCE = Mappers.getMapper(BeerMapper.class);

    BeerDto beerToBeerDto(Beer beer);
    Beer beerDtoToBeer(BeerDto beerDto);
}
