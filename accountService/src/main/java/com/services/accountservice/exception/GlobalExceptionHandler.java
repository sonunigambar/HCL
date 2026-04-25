package com.services.accountservice.exception;

import com.services.accountservice.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFound(ResourceNotFoundException ex) {

        ErrorResponse error = new ErrorResponse(

                ex.getMessage(),

                HttpStatus.INTERNAL_SERVER_ERROR.value()

        );

        return new ResponseEntity<>(error, HttpStatus.OK);

    }
}
