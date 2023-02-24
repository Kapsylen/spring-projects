package com.example.springbootmongo.domain;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface EmployeeService {
    void createEmp(Employee employee);

    Mono<Employee> findByEmpId(String id);

    Flux<Employee> findAllEmp();

    Mono<Employee> updateEmp(Employee employee);

    Mono<Void> deleteEmp(String id);
}
