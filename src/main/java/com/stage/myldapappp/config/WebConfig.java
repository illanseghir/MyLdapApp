package com.stage.myldapappp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/search").setViewName("search");
        registry.addViewController("/modif").setViewName("modif");
        registry.addViewController("/add").setViewName("entry");
        registry.addViewController("/delete").setViewName("delete");
    }
}
