package com.example.springbootmongo.domain;

import com.example.springbootmongo.infrastructure.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.UUID;

import static com.mongodb.assertions.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataMongoTest
@ExtendWith(SpringExtension.class)
public class EmployeeServiceTest {

    EmployeeServiceImpl employeeService;

    @Autowired
    EmployeeRepository employeeRepository;

    @BeforeEach
    void setUp() {
        employeeService = new EmployeeServiceImpl(employeeRepository);
    }

    @Test
    public void givenName_whenFindAllByName_thenFindEmployees() {
        var name = UUID.randomUUID().toString();
        var role = UUID.randomUUID().toString();
        employeeRepository.save(new Employee( name, role)).block();
        Flux<Employee> employeeFlux = employeeRepository.findAllByName(name);

        StepVerifier
                .create(employeeFlux)
                .assertNext(employee -> {
                    assertEquals(name, employee.getName());
                    assertEquals(role , employee.getRole());
                    assertNotNull(employee.getId());
                })
                .expectComplete()
                .verify();
    }

    @Test
    public void givenRole_whenFindAllByRole_thenFindEmployees() {
        var name = UUID.randomUUID().toString();
        var role = UUID.randomUUID().toString();
        employeeRepository.save(new Employee(name, role)).block();
        Flux<Employee> accountFlux = employeeRepository.findAllByRole(role);

        StepVerifier
                .create(accountFlux)
                .assertNext(employee -> {
                    assertEquals(name, employee.getName());
                    assertEquals(role , employee.getRole());
                    assertNotNull(employee.getId());
                })
                .expectComplete()
                .verify();
    }
}
