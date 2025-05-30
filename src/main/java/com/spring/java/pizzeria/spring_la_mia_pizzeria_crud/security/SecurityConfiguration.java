package com.spring.java.pizzeria.spring_la_mia_pizzeria_crud.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    @SuppressWarnings("removal") 
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        http.authorizeRequests()
        .requestMatchers(HttpMethod.POST, "/ingredient/create", "/ingredint/edit").hasAnyAuthority("ADMIN")
        .requestMatchers(HttpMethod.POST, "/pizze/create", "/pizze/edit").hasAuthority("ADMIN")
        .requestMatchers("/ingredient", "/ingredient/**").hasAuthority("ADMIN")
        .requestMatchers( "/home","/pizzas", "/pizzas/**").hasAnyAuthority("ADMIN","USER")
        .requestMatchers("/**").permitAll()
        .and().formLogin()
        .defaultSuccessUrl("/home", true)
        .and().logout()
        .and().exceptionHandling()
        
        // PER TESTING IN POSTMAN rotte POST
        .and().csrf().disable();


        
        return http.build();
    }
   

    @Bean
    @SuppressWarnings("deprecation")
    DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider= new DaoAuthenticationProvider();
        // user detail service
        authenticationProvider.setUserDetailsService(userDetailService());

        // scegliere il password encoder stabilito
        authenticationProvider.setPasswordEncoder(passwordEncoder());

        return authenticationProvider;
    }
    

    @Bean
    DatabaseUserDetailService userDetailService(){
        return new DatabaseUserDetailService();
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }



}
