package com.spring.java.pizzeria.spring_la_mia_pizzeria_crud.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.spring.java.pizzeria.spring_la_mia_pizzeria_crud.model.Pizza;
import com.spring.java.pizzeria.spring_la_mia_pizzeria_crud.model.SpecialOffer;
import com.spring.java.pizzeria.spring_la_mia_pizzeria_crud.repo.PizzaRepository;

@Service
public class PizzaService {

    
    @Autowired
    private SpecialOfferService offerService;


    @Autowired
    private PizzaRepository pizzaRepository;

    public List<Pizza> findAll() {

        return pizzaRepository.findAll();
    }

    public List<Pizza> findAllSortedByName() {
        return pizzaRepository.findAll(Sort.by("name"));
    }

    public List<Pizza> findAllSortedByPrice() {
        return pizzaRepository.findAll(Sort.by("price"));
    }

    public Pizza getByid(Integer id) {

        Optional<Pizza> pizzaAttempt = pizzaRepository.findById(id);

        // if (pizzaAttempt.isEmpty()) {
        // // return pizzaAttempt.get();
        // }

        return pizzaAttempt.get();

    }

    public Optional<Pizza> findById(Integer id) {
        return pizzaRepository.findById(id);
    }

    public List<Pizza> findByNameOrDescription(String query) {

        return pizzaRepository.findByNameContainingOrDescriptionContaining(query, query);

    }

    public Pizza create(Pizza pizza) {
        return pizzaRepository.save(pizza);
    }

    public Pizza updatePizza(Pizza pizza) {
        return pizzaRepository.save(pizza);
    }

    public void delate(Pizza pizza) {

        for (SpecialOffer offerDelete : pizza.getOffers()) {
            offerService.delete(offerDelete);
        }

        pizzaRepository.delete(pizza);
    }

    public void delateById(Integer id) {

        Pizza pizza = getByid(id);

        for (SpecialOffer offerDelete : pizza.getOffers()) {
            offerService.delete(offerDelete);
        }

        pizzaRepository.delete(pizza);
    }

    public boolean existById(Integer id) {
        return pizzaRepository.existsById(id);
    }

    public Boolean exist(Pizza pizza) {
        return pizzaRepository.existsById(pizza.getId());
    }

}
