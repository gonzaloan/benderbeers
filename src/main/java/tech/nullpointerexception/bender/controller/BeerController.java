package tech.nullpointerexception.bender.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Range;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import tech.nullpointerexception.bender.dto.BeerDto;
import tech.nullpointerexception.bender.dto.BeerPriceDto;
import tech.nullpointerexception.bender.exception.BeerException;
import tech.nullpointerexception.bender.exception.NotFoundException;
import tech.nullpointerexception.bender.service.BeerService;
import tech.nullpointerexception.bender.util.UtilConstants;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/beers")
@RequiredArgsConstructor
@Validated
@Slf4j
public class BeerController {

    private final BeerService beerService;


    /**
     * Obtiene todas las cervezas. En caso de haber un error,
     * retorna una lista vacía.
     *
     * @return
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<BeerDto> getAllBeers() {
        log.info("Dentro de GetAllBeers");
        return Optional.ofNullable(beerService.getAllBeers())
                .orElse(Collections.emptyList());
    }

    /**
     * Obtiene una cerveza por el ID
     *
     * @param beerId: Identificador de la cerveza a buscar.
     * @return
     */
    @GetMapping("/{beerID}")
    @ResponseStatus(HttpStatus.OK)
    public BeerDto getBeerById(@Valid @Range(min = 1, message = UtilConstants.BEER_ID_INVALID) @PathVariable("beerID") Integer beerId) {
        log.info("Dentro de getBeerById con ID {}", beerId);
        return Optional.ofNullable(beerService.getBeerById(beerId))
                .orElseThrow(NotFoundException::new);
    }

    /**
     * Crea una nueva cerveza.
     *
     * @param beerDto
     * @return
     */
    @PostMapping
    public ResponseEntity<BeerDto> handleBeerCreation(@Valid @NotNull @RequestBody BeerDto beerDto) {
        log.info("Dentro de handleBeerCreation con ID {}", beerDto.toString());
        BeerDto savedBeer = Optional.ofNullable(beerService.createBeer(beerDto))
                .orElseThrow(() -> new BeerException(UtilConstants.ERROR_MESSAGE_WHILE_CREATING_BEER));
        HttpHeaders headers = new HttpHeaders();
        //entregamos la ruta del nuevo elemento creado.
        try {
            headers.add("Location", InetAddress.getLocalHost().getHostAddress() + "/beers/" + savedBeer.getId().toString());
        } catch (UnknownHostException e) {
            headers.add("Location", "/beers/" + savedBeer.getId().toString());
        }
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    /**
     * Lista el precio de una caja de cervezas de una marca
     *
     * @param beerId: Identificador de la cerveza a buscar.
     * @return
     */
    @GetMapping("/{beerID}/boxprice")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public BeerPriceDto getPriceListById(@Valid @Range(min = 1, message = UtilConstants.BEER_ID_INVALID) @PathVariable("beerID") Integer beerId,
                                         @NotNull(message = UtilConstants.CURRENCY_CANT_BE_NULL) @RequestParam(name = "currency") String currency,
                                         @Valid @Range(min = 0, message = UtilConstants.QUANTITY_INVALID
                                         ) @RequestParam(name = "quantity") Integer quantity) {
        log.info("Dentro de getPriceListById con ID {}, currenty {} y quantity {}", beerId, currency, quantity);
        return Optional.ofNullable(beerService.getBeerListedPriceByCurrencyAndQuantity(beerId, currency, quantity))
                .orElseThrow(NotFoundException::new);

    }


}
