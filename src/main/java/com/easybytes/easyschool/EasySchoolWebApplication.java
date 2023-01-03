package com.easybytes.easyschool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.easybytes.easyschool.repository")
@EntityScan("com.easybytes.easyschool.model")
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl") //bean name auditAwareImpl
public class EasySchoolWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(EasySchoolWebApplication.class, args);
    }

}
