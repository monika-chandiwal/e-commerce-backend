package com.backend.vendor.config;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfigurationSource;

@Order(1)
@Configuration
@EnableWebSecurity
public class VendorSecurityConfig {
    @Bean
    public SecurityFilterChain vendorFilterChain(HttpSecurity http, CorsConfigurationSource corsConfigurationSource) throws Exception {
        http
                .securityMatcher("/vendor/**")
                .csrf(AbstractHttpConfigurer::disable)
                .cors(cors -> cors.configurationSource(corsConfigurationSource))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                        .requestMatchers("/vendor/public/**", "/vendor/verifyOtp", "/vendor/otpRequest","/vendor/signup","/vendor/login","/vendor/logout","/vendor/addProduct","/vendor/dashboard/**","/vendor/dashboard/deleteProduct/*","/vendor/dashboard/deleteAllProduct","/vendor/dashboard/productUpdate/*").permitAll()
                        .anyRequest().authenticated()
                )
                .oauth2Login(AbstractHttpConfigurer::disable)  // <-- DISABLE OAUTH2 LOGIN HERE
                .logout(logout -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/vendor/logout"))
                        .logoutSuccessHandler((request, response, authentication) -> response.setStatus(HttpServletResponse.SC_OK))
                        .invalidateHttpSession(true)
                        .clearAuthentication(true)
                        .deleteCookies("JSESSIONID")
                );

        return http.build();
    }
}

