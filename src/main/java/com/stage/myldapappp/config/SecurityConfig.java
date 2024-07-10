package com.stage.myldapappp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers("/", "/search", "/modif", "/add", "/css/**").permitAll()
                        .anyRequest().authenticated()
                )
                /*
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers("/", "/search", "/modif", "/add", "/css/**").permitAll()
                        .anyRequest().authenticated()
                )*/
                /*
                .formLogin((form) -> form
                        .loginPage("/login")
                        .permitAll()
                )
                 */
                .logout(LogoutConfigurer::permitAll);

        return http.build();
    }
}
