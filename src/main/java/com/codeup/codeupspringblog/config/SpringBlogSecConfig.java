package com.codeup.codeupspringblog.config;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.cglib.proxy.NoOp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SpringBlogSecConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((requests) -> requests
                .requestMatchers("/posts/create","/posts/comment","/posts/{id}/edit","/logout", "/profile").authenticated()
                .requestMatchers("/posts","/posts/*/view", "/register", "/login").permitAll()
                .requestMatchers("/css/**", "/js/**").permitAll());

        http.formLogin(
//                withDefaults()
                (form) -> form
                .loginPage("/login")
                .defaultSuccessUrl("/posts")
        );
        http.logout(
                (logout) -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login"));
//        http.authorizeHttpRequests((requests) -> requests
//                .anyRequest().permitAll()
//        );
        http.httpBasic(withDefaults());
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
        };
    }
