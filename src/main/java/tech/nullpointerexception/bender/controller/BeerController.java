package tech.nullpointerexception.bender.controller;

import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/beers")
public class BeerController {

    @Autowired
    private BeerService beerService;


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<BeerDto> getAllBeers() {
        return Optional.ofNullable(beerService.getAllBeers())
                .orElse(Collections.emptyList());
    }

    @GetMapping("/{beerID}")
    @ResponseStatus(HttpStatus.OK)
    public BeerDto getBeerById(@PathVariable("beerID") Integer beerId) throws Exception{
        return Optional.ofNullable(beerService.getBeerById(beerId))
                .orElseThrow(Exception::new);
    }

    @PostMapping
    public ResponseEntity<BeerDto> handleBeerCreation(@RequestBody BeerDto beerDto) {
        BeerDto savedBeer = Optional.ofNullable(beerService.createBeer(beerDto))
                .orElseThrow(() -> new BeerException(UtilConstants.ERROR_MESSAGE_WHILE_CREATING_BEER));
        HttpHeaders headers = new HttpHeaders();
        try {
            headers.add("Location", InetAddress.getLocalHost().getHostAddress() + "/beers/" + savedBeer.getId().toString());
        } catch (UnknownHostException e) {
            headers.add("Location", "/beers/" + savedBeer.getId().toString());
        }
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

}
