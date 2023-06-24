package com.example.demo;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests() // nuestros requests requieren autenticacion
            .anyRequest() // Todos
            .authenticated() // forsozamente un cliente autenticado con su token valido
            .and()
            .oauth2Login()
            .and()
            .oauth2ResourceServer()
            .jwt();
    }
    
}
