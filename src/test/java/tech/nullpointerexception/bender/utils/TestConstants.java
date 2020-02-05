package tech.nullpointerexception.bender.utils;

import tech.nullpointerexception.bender.dto.BeerDto;

import java.util.Arrays;
import java.util.List;

public class TestConstants {

    /**
     * Retorna listado de cervezas para testear
     */
    public static List<BeerDto> GET_BEER_LIST() {
        return Arrays.asList(BeerDto.builder()
                        .id(1)
                        .name("Cuello Negro Stout")
                        .brewery("Cuello Negro")
                        .country("Chile")
                        .currency("CL")
                        .price(1800.0)
                        .build(),
                BeerDto.builder()
                        .id(2)
                        .name("Kross Golden")
                        .brewery("Cervecería Kross")
                        .country("Chile")
                        .currency("CL")
                        .price(1500.0)
                        .build());
    }

    public static BeerDto GET_NEW_BEER(){
        return BeerDto.builder()
                .id(5)
                .name("Cerveza JUNIT")
                .country("USA")
                .brewery("Spring Test Cervezas")
                .currency("CLP")
                .price(1900.0)
                .build();
    }


}
