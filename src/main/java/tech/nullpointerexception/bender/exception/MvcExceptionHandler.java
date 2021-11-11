package tech.nullpointerexception.bender.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import tech.nullpointerexception.bender.util.UtilConstants;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@ControllerAdvice
@RestController
public class MvcExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(value = BAD_REQUEST)
    public ErrorResponse handleConstraintsError(ConstraintViolationException e) {

        List<String> errorDetails = e.getConstraintViolations().isEmpty() ? Collections.singletonList(UtilConstants.ERROR_GETTING_DATA) :
                e.getConstraintViolations().stream()
                        .map(ConstraintViolation::getMessage)
                        .collect(Collectors.toList());
        return new ErrorResponse(LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                UtilConstants.ERROR_MESSAGE,
                errorDetails
                );
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(value = NOT_FOUND)
    public ErrorResponse handleExceptionNotFound() {
        return new ErrorResponse(LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                UtilConstants.ERROR_MESSAGE,
                Collections.singletonList(UtilConstants.NOT_FOUND_DATA)
        );
    }


    @ExceptionHandler(value = {BeerException.class})
    @ResponseStatus(value = INTERNAL_SERVER_ERROR)
    public ErrorResponse handleExceptionGeneral(RuntimeException e) {
        return new ErrorResponse(LocalDateTime.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                UtilConstants.ERROR_MESSAGE,
                Collections.singletonList(e.getMessage())
        );
    }


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> details = new ArrayList<>();
        for (ObjectError error : ex.getBindingResult().getAllErrors()) {
            details.add(error.getDefaultMessage());
        }
        ErrorResponse errorResponse = new ErrorResponse(LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                UtilConstants.ERROR_MESSAGE,
                details
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
