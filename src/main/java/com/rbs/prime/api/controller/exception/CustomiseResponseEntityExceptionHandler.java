package com.rbs.prime.api.controller.exception;

import com.rbs.prime.api.util.ClockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;

@ControllerAdvice
@RestController
public class CustomiseResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private ClockService clockService;

    /**
     * Default handler for all exceptions. Internal server error is returned
     */
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllException(Exception ex, WebRequest request) {
        ExceptionResponse response = new ExceptionResponse(clockService.currentTime(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Default for IllegalArgumentException.
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public final ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException ex, WebRequest request) {
        ExceptionResponse response = new ExceptionResponse(clockService.currentTime(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
