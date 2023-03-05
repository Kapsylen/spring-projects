package com.example.springbootmongo.exception;

import org.zalando.problem.AbstractThrowableProblem;

import java.net.URI;

public class EmployeeApiException extends AbstractThrowableProblem {
    public EmployeeApiException(String message) {
        super(URI.create("URI"), message);
    }
}
