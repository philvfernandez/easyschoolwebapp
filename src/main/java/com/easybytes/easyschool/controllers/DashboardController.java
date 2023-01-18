package com.easybytes.easyschool.controllers;

import com.easybytes.easyschool.model.Person;
import com.easybytes.easyschool.repository.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Slf4j
@Controller
public class DashboardController {

    @Autowired
    PersonRepository personRepository;

    @Value("${easyschool.pageSize}")
    private int defaultPageSize;

    @Value("${easyschool.contact.successMsg}")
    private String message;

    @RequestMapping("/dashboard")
    public String displayDashboard(Model model, Authentication authentication, HttpSession httpSession) {
        Person person = personRepository.readByEmail(authentication.getName());

        model.addAttribute("username", person.getName());
        model.addAttribute("roles", authentication.getAuthorities().toString());

        //if(person.getEazyClass() != null && person.getEazyClass().getName() != null) {
        if(null != person.getEazyClass() && null != person.getEazyClass().getName()){
            model.addAttribute("enrolledClass", person.getEazyClass().getName());
        }

        //set person object in session.
        httpSession.setAttribute("loggedInPerson", person);

        logMessages();

        return "dashboard.html";
    }

    private void logMessages() {
        log.error("Error message from the Dashboard page.");
        log.warn("Warning message from the Dashboard page.");
        log.info("Info message from the Dashboard page.");
        log.debug("Debug message from the Dashboard page.");
        log.trace("Trace message from the Dashboard page.");

        log.error("defaultPageSize value with @Value annotation is: " + defaultPageSize);
        log.error("successMsg value with @Value annotation is: " + message);
    }
}
