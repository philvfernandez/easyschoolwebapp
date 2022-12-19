package com.easybytes.easyschool.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
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

        http.csrf().ignoringAntMatchers("/saveMsg").ignoringAntMatchers("/h2-console/**").and()
                .authorizeHttpRequests()
                .mvcMatchers("/dashboard").authenticated()
                .mvcMatchers("/displayMessages").hasRole("ADMIN")
                .mvcMatchers("/home").permitAll()
                .mvcMatchers("/holidays/**").permitAll()
                .mvcMatchers("/contact").permitAll()
                .mvcMatchers("/saveMsg").permitAll()
                .mvcMatchers("/courses").permitAll()
                .mvcMatchers("/about").permitAll()
                .mvcMatchers("/login").permitAll()
                .mvcMatchers("/logout").permitAll()
                .mvcMatchers("/h2-console/**").permitAll()
                .and().formLogin().loginPage("/login")
                .defaultSuccessUrl("/dashboard").failureUrl("/login?error=true").permitAll()
                .and().logout().logoutSuccessUrl("/login?logout=true").invalidateHttpSession(true).permitAll()
                //.and().authorizeRequests().antMatchers("/h2-console/**").permitAll()
                .and().httpBasic();

        http.headers().frameOptions().disable();
        return http.build();
    }


    //TODO: DO NOT PUT IN PRODUCTION!! DEMO ONLY!
    @Bean
    public InMemoryUserDetailsManager userDetailsManager() {

        UserDetails user = User.withDefaultPasswordEncoder()
                .username("user")
                .password("12345")
                .roles("USER")
                .build();

        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("54321")
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user, admin);
    }

}
