package com.mystore.spring.boot.fakestore.controller;

import com.mystore.spring.boot.fakestore.dto.ExceptionDTO;
import com.mystore.spring.boot.fakestore.exception.NoRecordFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(NoRecordFoundException.class)
    private ResponseEntity<ExceptionDTO> handleNoRecordFoundException(NoRecordFoundException exception){
        return new ResponseEntity<>(
                new ExceptionDTO(HttpStatus.NOT_FOUND,exception.getMessage()),
                HttpStatus.NOT_FOUND);
    }
}
