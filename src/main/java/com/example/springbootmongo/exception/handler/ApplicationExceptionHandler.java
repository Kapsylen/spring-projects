package com.example.springbootmongo.exception.handler;

import com.example.springbootmongo.api.response.EmployeeNotFoundApi;
import com.example.springbootmongo.exception.EmployeeNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.zalando.problem.Status;

@RestControllerAdvice
@Slf4j
public class ApplicationExceptionHandler {

    @ExceptionHandler(value = EmployeeNotFoundException.class)
    public ResponseEntity<EmployeeNotFoundApi> handleInvoiceNotFoundException(EmployeeNotFoundException exception) {
        var responseEntity = ResponseEntity.status(Status.NOT_FOUND.getStatusCode()).body(
                new EmployeeNotFoundApi(exception.getTitle(),exception.getMessage(),  exception.getDetail()));
        log.info("ResponseEntity: {}", responseEntity);
        return responseEntity;
    }
}
