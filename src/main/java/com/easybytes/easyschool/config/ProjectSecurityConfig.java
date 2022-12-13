package com.easybytes.easyschool.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectSecurityConfig /*extends WebSecurityConfigurerAdapter*/ {

    /* @Override
    protected void configure(HttpSecurity http) throws Exception {

        //Permit All requests inside the Web Application
        http.authorizeRequests().
                anyRequest().permitAll().
                and().formLogin()
                .and().httpBasic();

        //Deny All Requests inside the Web Application
        http.authorizeRequests().
                anyRequest().denyAll().
                and().formLogin()
                .and().httpBasic()



    } */

    /**
     * From Spring Security 5.7, the WebSecurityConfigurerAdapter is deprecated to encourage users
     * to move towards a component-based security configuration. It is recommended to create a bean
     * of type SecurityFilterChain for security related configurations.
     * @param http
     * @return SecurityFilterChain
     * @throws Exception
     */
    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {

        http.csrf().disable()
                .authorizeHttpRequests()
                .regexMatchers("/home").permitAll()
                .regexMatchers("/holidays/*").permitAll()
                .regexMatchers("/contact").permitAll()
                .regexMatchers("/saveMsg").permitAll()
                .regexMatchers("/courses").permitAll()
                .regexMatchers("/assets/*").permitAll()
                .and().formLogin().and().httpBasic();

        return http.build();
    }

}
