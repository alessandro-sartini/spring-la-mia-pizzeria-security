package com.spring.java.pizzeria.spring_la_mia_pizzeria_crud.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.spring.java.pizzeria.spring_la_mia_pizzeria_crud.model.Pizza;
import com.spring.java.pizzeria.spring_la_mia_pizzeria_crud.services.PizzaService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@CrossOrigin
@RequestMapping("/api/pizzas")
public class PizzaRestController {

    @Autowired
    PizzaService service;
   
    @GetMapping
    public List<Pizza> index() {

        return service.findAll();
    }

    @GetMapping("/byName")
    public List<Pizza> indexByName() {

        return service.findAllSortedByName();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pizza> show(@PathVariable Integer id) {
        Optional<Pizza> optionalPizza = service.findById(id);

        if (optionalPizza.isEmpty()) {
            return new ResponseEntity<Pizza>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Pizza>(optionalPizza.get(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Pizza> store(@Valid @RequestBody Pizza pizza) {
        return new ResponseEntity<Pizza>(
                service.create(pizza), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Pizza> patch(@PathVariable Integer id,
            @Valid @RequestBody Pizza pizza) {

        if (!service.existById(id)) {
            return new ResponseEntity<Pizza>(HttpStatus.NOT_FOUND);
        }

        pizza.setId(id);
        return new ResponseEntity<Pizza>(
                service.updatePizza(pizza), HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Pizza> destroy(@PathVariable Integer id) {

        if (!service.existById(id)) {
            return new ResponseEntity<Pizza>(HttpStatus.NOT_FOUND);
        }
        service.delateById(id);
        return new ResponseEntity<Pizza>(HttpStatus.OK);

    }

}
