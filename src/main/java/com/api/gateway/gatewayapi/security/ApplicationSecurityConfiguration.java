package com.api.gateway.gatewayapi.security;

import com.api.gateway.gatewayapi.filter.security.AuthenticationFilter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    AuthenticationFilter authenticationFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors()
            .and()
            .csrf().disable()
            .authorizeRequests()
            .antMatchers(HttpMethod.POST, "/api/authenticationService/*").permitAll()
            .antMatchers(HttpMethod.GET, "/api/taskService/tasks").permitAll()
            .antMatchers(HttpMethod.POST, "/api/taskService/tasks").denyAll()
            .antMatchers(HttpMethod.GET, "/api/taskService/tasks/*").permitAll()
            .antMatchers(HttpMethod.GET, "/api/taskService/tasks/ofuser/*").denyAll()
            .antMatchers(HttpMethod.GET, "/api/compose/**").permitAll()
            .antMatchers(HttpMethod.POST, "/api/compose/**").permitAll()
            .antMatchers("/**").denyAll()
            .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
            .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);

    }


}
