package com.spring.java.pizzeria.spring_la_mia_pizzeria_crud.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
        
        
        
        @Bean
        DatabaseUserDetailService userDetailService() {
                return new DatabaseUserDetailService();
        }
        
        // Bean per la codifica delle password
        @Bean
        PasswordEncoder passwordEncoder() {
                return PasswordEncoderFactories.createDelegatingPasswordEncoder();
        }
        
        @Bean
        @SuppressWarnings("deprecation")
        DaoAuthenticationProvider authenticationProvider() {
                DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
                
                authenticationProvider.setUserDetailsService(userDetailService());
                
                authenticationProvider.setPasswordEncoder(passwordEncoder());
                
                return authenticationProvider;
        }
        
        // --- PRIMA CATENA DI FILTRI: PER LE API (API Key Authentication) ---
        // Questa catena gestisce solo le richieste che iniziano con /api/
        @Bean
        SecurityFilterChain apiFilterChain(HttpSecurity http) throws Exception {
                http
                .securityMatcher("/api/**") 
                .csrf(AbstractHttpConfigurer::disable) // Disabilita CSRF per le API 
                .authorizeHttpRequests(authorize -> authorize
                // Tutte le richieste che corrispondono a /api/** devono essere
                // autenticate
                
                .anyRequest().authenticated())
                // Utilizza l'autenticazione stateless per le API 
                .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                // Aggiungi il tuo filtro personalizzato prima del filtro di autenticazione
                // standard
                .addFilterBefore(new AuthenticationFilter(),
                UsernamePasswordAuthenticationFilter.class);
                
                return http.build();
        }
        
        
        // Questa catena gestisce tutti gli altri percorsi che non sono stati gestiti da
        // apiFilterChain
        @Bean
        SecurityFilterChain webFilterChain(HttpSecurity http) throws Exception {
                http
                
                .csrf(Customizer.withDefaults()) 
                .authorizeHttpRequests(authorize -> authorize
                
                .requestMatchers(HttpMethod.POST, "/ingredient/create",
                "/ingredient/edit")
                .hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.POST, "/pizze/create", "/pizze/edit")
                .hasAuthority("ADMIN")
                .requestMatchers("/ingredient", "/ingredient/**").hasAuthority("ADMIN")
                
                
                .requestMatchers("/home", "/pizzas", "/pizzas/**")
                .hasAnyAuthority("ADMIN", "USER")
                
                
                .anyRequest().authenticated())
                .formLogin(form -> form 
                .defaultSuccessUrl("/home", true)
                )
                .logout(logout -> logout 
                .logoutSuccessUrl("/") 
                )
                .exceptionHandling(ex -> ex
                .accessDeniedPage("/access-denied") 
                );
                
                return http.build();
        }
}



// package com.spring.java.pizzeria.spring_la_mia_pizzeria_crud.security;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.http.HttpMethod;
// import
// org.springframework.security.authentication.dao.DaoAuthenticationProvider;
// import
// org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import
// org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.crypto.factory.PasswordEncoderFactories;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.security.web.SecurityFilterChain;

// @Configuration
// @EnableWebSecurity
// public class SecurityConfiguration {

// @Bean
// @SuppressWarnings("removal")
// SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

// http.authorizeRequests()
// .requestMatchers(HttpMethod.POST, "/ingredient/create",
// "/ingredint/edit").hasAnyAuthority("ADMIN")
// .requestMatchers(HttpMethod.POST, "/pizze/create",
// "/pizze/edit").hasAuthority("ADMIN")
// .requestMatchers("/ingredient", "/ingredient/**").hasAuthority("ADMIN")
// .requestMatchers( "/home","/pizzas",
// "/pizzas/**").hasAnyAuthority("ADMIN","USER")
// .requestMatchers("/**").permitAll()
// .and().formLogin()
// .defaultSuccessUrl("/home", true)
// .and().logout()
// .and().exceptionHandling()

// // PER TESTING IN POSTMAN rotte POST
// .and().csrf().disable();

// return http.build();
// }

// @Bean
// @SuppressWarnings("deprecation")
// DaoAuthenticationProvider authenticationProvider(){
// DaoAuthenticationProvider authenticationProvider= new
// DaoAuthenticationProvider();
// // user detail service
// authenticationProvider.setUserDetailsService(userDetailService());

// // scegliere il password encoder stabilito
// authenticationProvider.setPasswordEncoder(passwordEncoder());

// return authenticationProvider;
// }

// @Bean
// DatabaseUserDetailService userDetailService(){
// return new DatabaseUserDetailService();
// }

// @Bean
// PasswordEncoder passwordEncoder(){
// return PasswordEncoderFactories.createDelegatingPasswordEncoder();
// }

// }