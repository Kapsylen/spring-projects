package com.example.springbootmongo.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Scope(scopeName = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Document
@NoArgsConstructor
@Getter
@Setter
public class Employee {
    @Id
    String id;
    String name;
    String role;

    public Employee(String name, String role) {
        this.name = name;
        this.role = role;
    }
}