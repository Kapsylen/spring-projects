package com.example.springbootmongo.exception;

import org.springframework.http.HttpStatus;
import org.zalando.problem.AbstractThrowableProblem;

import java.net.URI;

public class EmployeeNotFoundException extends AbstractThrowableProblem {
    public EmployeeNotFoundException(String details) {
        super(URI.create("URI"), HttpStatus.NOT_FOUND.toString(), null, details);
    }
}
