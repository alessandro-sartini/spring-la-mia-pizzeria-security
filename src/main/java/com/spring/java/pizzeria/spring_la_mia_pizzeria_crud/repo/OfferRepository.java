package com.spring.java.pizzeria.spring_la_mia_pizzeria_crud.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.java.pizzeria.spring_la_mia_pizzeria_crud.model.SpecialOffer;

public interface OfferRepository extends JpaRepository<SpecialOffer, Integer>{

}
