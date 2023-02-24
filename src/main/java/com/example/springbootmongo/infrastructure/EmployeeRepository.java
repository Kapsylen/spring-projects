package com.example.springbootmongo.infrastructure;

import com.example.springbootmongo.domain.Employee;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface EmployeeRepository extends ReactiveMongoRepository<Employee, String> {
    Flux<Employee> findAllByName(String name);

    Flux<Employee> findAllByRole(String role);
}
