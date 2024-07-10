package com.stage.myldapappp;

import com.stage.myldapappp.config.SecurityConfig;
import com.stage.myldapappp.config.WebConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.reactive.ReactiveSecurityAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication(exclude = {ReactiveSecurityAutoConfiguration.class})
public class MyldapapppApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyldapapppApplication.class, args);
    }
}
