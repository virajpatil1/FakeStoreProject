package com.shoppingcart.productserviceshoppingcart.security.configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;

//
//@Configuration
//public class SecurityConfig {
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//
//        http
//			.authorizeHttpRequests((authorize) -> authorize
//                .anyRequest().authenticated()
//            )
//                .oauth2ResourceServer((oauth2) -> oauth2
//                        .jwt(Customizer.withDefaults())
//                );
//
//        http.cors(cors -> cors.disable());
//        http.csrf(csrf -> csrf.disable());
//        return http.build();
//    }
//
//}
