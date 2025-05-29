package com.spring.java.pizzeria.spring_la_mia_pizzeria_crud.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.java.pizzeria.spring_la_mia_pizzeria_crud.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(String username); 

}
