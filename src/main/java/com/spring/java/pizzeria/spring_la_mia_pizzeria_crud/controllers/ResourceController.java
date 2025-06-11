package com.spring.java.pizzeria.spring_la_mia_pizzeria_crud.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResourceController {
    @GetMapping("/api/home")
    public String homeEndpoint() {
        return "Baeldung !";
    }
}