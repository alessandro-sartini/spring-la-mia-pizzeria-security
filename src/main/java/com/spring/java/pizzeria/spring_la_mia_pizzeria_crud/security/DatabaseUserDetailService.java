package com.spring.java.pizzeria.spring_la_mia_pizzeria_crud.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.spring.java.pizzeria.spring_la_mia_pizzeria_crud.model.User;
import com.spring.java.pizzeria.spring_la_mia_pizzeria_crud.repo.UserRepository;

@Service
public class DatabaseUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    // per ottenre i dati usera la repository per ottenerli
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        Optional<User> userAttempt= userRepository.findByUsername(username);
        if (userAttempt.isEmpty()) {
            
            throw new UsernameNotFoundException("No user found with "+username);
        }
        return new DatabaseUserDetails(userAttempt.get());
    }
    
}
