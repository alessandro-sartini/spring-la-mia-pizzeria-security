package com.spring.java.pizzeria.spring_la_mia_pizzeria_crud.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.java.pizzeria.spring_la_mia_pizzeria_crud.model.Pizza;

public interface PizzaRepository extends JpaRepository<Pizza, Integer> {

    public List<Pizza> findByName(String name);

    public List<Pizza> findByNameContaining(String name);

    public List<Pizza> findByNameContainingOrDescriptionContaining(String name, String description);
}
