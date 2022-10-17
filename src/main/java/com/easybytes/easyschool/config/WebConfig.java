package com.easybytes.easyschool.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/*
Registers view controllers that create a direct mapping between the URL and the
view name using the ViewControllerRegistry.  This way, there's no need for any
Controller between the two.
*/
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/courses").setViewName("courses");

        //TODO: To be added later
        //registry.addViewController("/about").setViewName("about");
    }
}
