package com.easybytes.easyschool.controllers;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class ContactController {

    //This can be used in place of the @Slf4j annotation.
    //private static Logger log = LoggerFactory.getLogger(ContactController.class);

    @RequestMapping("/contact")
    public String displayContactPage() {
        return "contact.html";
    }

    @RequestMapping(value = "/saveMsg", method = RequestMethod.POST)
    public ModelAndView saveMessage(@RequestParam String name, @RequestParam String mobileNum,
                                    @RequestParam String email, @RequestParam String subject, @RequestParam String message) {
        log.info("Name: " + name);
        log.info("Mobile Number: " + mobileNum);
        log.info("Email Address: " + email);
        log.info("Subject: " + subject);
        log.info("Message: " + message);
        return new ModelAndView("redirect:/contact");
    }
}
