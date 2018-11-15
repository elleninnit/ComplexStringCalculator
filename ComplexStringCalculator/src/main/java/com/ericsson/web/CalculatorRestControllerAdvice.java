package com.ericsson.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class CalculatorRestControllerAdvice {

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseBody
    public final ResponseEntity<Object> handleIllegalArgumentException(String message) {
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

}
