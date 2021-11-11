package tech.nullpointerexception.bender.utils;

import tech.nullpointerexception.bender.dto.BeerDto;
import tech.nullpointerexception.bender.model.Beer;

import java.util.Arrays;
import java.util.List;

public class TestConstants {

    /**
     * Retorna listado de cervezas para testear
     */
    public static List<BeerDto> GET_BEER_LIST() {
        return Arrays.asList(new BeerDto(1,
                "Cuello Negro Stout",
                "Cuello Negro",
                "Chile",
                1800.0,
                "CLP"),
                new BeerDto(2,
                        "Kross Golden",
                        "Cervecería Kross",
                        "Chile",
                        1500.0,
                        "CL"));
    }

    public static BeerDto GET_NEW_BEER(){
        return new BeerDto(5, "Cerveza JUNIT", "Spring Test Cervezas", "USA", 1900.0, "CLP");
    }
    public static Beer GET_NEW_BEER_MODEL(){
        return new Beer(5, "Cerveza JUNIT", "Spring Test Cervezas", "USA", 1900.0, "CLP");
    }
}
