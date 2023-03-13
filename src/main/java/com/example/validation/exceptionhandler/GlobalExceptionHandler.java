package com.example.validation.exceptionhandler;

import com.example.validation.dto.ConstrainViolation;
import com.example.validation.dto.ErrorResponse;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestControllerAdvice("com.example.validation")
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleIllegalArgumentException(IllegalArgumentException exception){
        return ErrorResponse.builder()
                .message(exception.getMessage())
                .build();
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleAllExceptions (Exception exception){
        return ErrorResponse.builder()
                .message(exception.getMessage())
                .build();
    }

    // Adding new method to handle exception throwed by hibernate validation.
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException exception){
        List<ConstrainViolation> errors =exception.getFieldErrors()
                .stream()
                .map(violation ->ConstrainViolation.builder()
                        .message(violation.getDefaultMessage())
                        .fieldName(violation.getField())
                        .rejectedValue(Objects.isNull(violation.getRejectedValue())?
                                "null": violation.getRejectedValue().toString())
                        .build())
                .collect(Collectors.toList());
        return ErrorResponse.builder()
                .message("Failed validation")
                .errors(errors)
                .build();
    }
}
