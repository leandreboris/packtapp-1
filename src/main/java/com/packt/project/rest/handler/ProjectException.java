package com.packt.project.rest.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class ProjectException {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getAllErrors().stream().forEach(
                error -> {
                    String fieldName = ((FieldError) error).getField();
                    String message = error.getDefaultMessage();
                    errors.put(fieldName, message);
                }
        );
        log.debug("------- methodArgumentNotValidExceptionHandler is called ------------");
        return errors;
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public Map<String, String> constraintViolationExceptionHandler(ConstraintViolationException e) {
        Map<String, String> errors = new HashMap<>();
        e.getConstraintViolations().stream().forEach(
                error -> {
                    String path = String.valueOf(error.getPropertyPath());
                    String message = error.getMessage();
                    errors.put(path, message);
                }
        );
        log.debug("------- constraintViolationExceptionHandler is called ------------");
        return errors;
    }

}




