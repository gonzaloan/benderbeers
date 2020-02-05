package tech.nullpointerexception.bender.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class BeerException extends RuntimeException {

    public BeerException(String error){
        super(error);
    }
}
