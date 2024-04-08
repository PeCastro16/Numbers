package com.challenge.numbers.adaptor.web.advice;

import com.challenge.numbers.adaptor.web.NumberController;
import com.challenge.numbers.adaptor.web.dto.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@Slf4j
@RestControllerAdvice(assignableTypes = {NumberController.class})
public class NumberControllerAdvice {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchElementException.class)
    public ErrorResponse notFound(NoSuchElementException e) {
        log.error(e.getMessage(), e);
        return new ErrorResponse("Number not found!");
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ErrorResponse genericError(Exception e) {
        log.error(e.getMessage(), e);
        return new ErrorResponse("Internal server error");
    }

}
