package com.spring.java.pizzeria.spring_la_mia_pizzeria_crud.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.java.pizzeria.spring_la_mia_pizzeria_crud.model.SpecialOffer;
import com.spring.java.pizzeria.spring_la_mia_pizzeria_crud.services.SpecialOfferService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/offer")
@CrossOrigin
public class SpecialOfferRestController {

    @Autowired
    private SpecialOfferService service;

    @GetMapping
    public List<SpecialOffer> index() {
        return service.findaAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SpecialOffer> show(@PathVariable Integer id) {

        Optional<SpecialOffer> offerAttempt = service.findById(id);

        if (offerAttempt.isEmpty()) {
            return new ResponseEntity<SpecialOffer>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<SpecialOffer>(offerAttempt.get(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<SpecialOffer> create(@Valid @RequestBody SpecialOffer offer) {

        return new ResponseEntity<SpecialOffer>(service.create(offer), HttpStatus.OK);
    }


    // @PutMapping("update/{id}")
    // public String putMethodName(@PathVariable String id, @RequestBody String entity) {
    //     //
        
    //     return entity;
    // }

}
