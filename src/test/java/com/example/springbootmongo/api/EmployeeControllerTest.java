package com.example.springbootmongo.api;

import com.example.springbootmongo.domain.Employee;
import com.example.springbootmongo.domain.EmployeeServiceImpl;
import com.example.springbootmongo.exception.EmployeeApiException;
import com.example.springbootmongo.exception.EmployeeNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.util.UUID;

import static org.mockito.Mockito.when;


@ExtendWith(SpringExtension.class)
@WebFluxTest(EmployeeController.class)
public class EmployeeControllerTest {

    @Autowired
    WebTestClient webClient;

    @MockBean
    EmployeeServiceImpl employeeService;

    @Test
    public void should_get_employee() {
        var empId = UUID.randomUUID().toString();
        var randomEmployee = new Employee(UUID.randomUUID().toString(), UUID.randomUUID().toString());
        when(employeeService.findByEmpId(empId)).thenReturn(Mono.just(randomEmployee));

        webClient.get().uri("/get/" + empId)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(Employee.class);
    }

    @Test
    public void should_save_employee() {
        var randomEmployee = new Employee(UUID.randomUUID().toString(), UUID.randomUUID().toString());
        when(employeeService.updateEmp(randomEmployee)).thenReturn(Mono.just(randomEmployee));
        webClient.post().uri("/create/emp")
                .bodyValue(randomEmployee)
                .exchange()
                .expectStatus()
                .isCreated();
    }

    @Test
    public void When_no_employee_were_found_Should_throw_EmployeeNotFoundException() {
        var empId = UUID.randomUUID().toString();
        when(employeeService.findByEmpId(empId)).thenReturn(Mono.error(new EmployeeNotFoundException("Not found: "+empId)));
        webClient.get().uri("/get/" + empId)
                .exchange()
                .expectStatus()
                .isNotFound();
    }
}

