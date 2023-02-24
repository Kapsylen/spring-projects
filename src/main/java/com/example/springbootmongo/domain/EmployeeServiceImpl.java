package com.example.springbootmongo.domain;

import com.example.springbootmongo.infrastructure.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {


    EmployeeRepository employeeRepo;

    public void createEmp(Employee employee) {
        employee.setId(UUID.randomUUID().toString());
        employeeRepo.save(employee).subscribe();
    }

    public Mono<Employee> findByEmpId(String id) {
        return employeeRepo.findById(id);
    }

    public Flux<Employee> findAllEmp() {
        return employeeRepo.findAll();
    }

    public Mono<Employee> updateEmp(Employee employee) {
        employee.setId(UUID.randomUUID().toString());
        return employeeRepo.save(employee);
    }

    public Mono<Void> deleteEmp(String id) {
        return employeeRepo.deleteById(id);
    }
}
