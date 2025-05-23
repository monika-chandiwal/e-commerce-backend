package com.backend.customer.config;

import com.backend.customer.model.Users;
import com.backend.customer.repository.UsersRepo;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.config.Customizer;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .securityMatcher(request -> !request.getRequestURI().startsWith("/vendor/")) // exclude vendor URLs
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login", "/signup", "/contactUs", "/oauth2/**").permitAll()
                        .requestMatchers("/current-user").authenticated()
                        .anyRequest().authenticated()
                )

                .cors(Customizer.withDefaults())

                .oauth2Login(oauth -> oauth

                        .defaultSuccessUrl("http://localhost:5173/oauth2/redirect", true)

                        .userInfoEndpoint(user -> user.userService(oauth2UserService()))
                )
                .logout(logout -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .logoutSuccessHandler((request, response, authentication) -> {
                            response.setStatus(HttpServletResponse.SC_OK);
                        })
                        .invalidateHttpSession(true)
                        .clearAuthentication(true)
                        .deleteCookies("JSESSIONID")
                );

        return http.build();
    }

    @Autowired
    private UsersRepo usersRepo;

    @Bean
    public OAuth2UserService<OAuth2UserRequest, OAuth2User> oauth2UserService() {
        return userRequest -> {
            OAuth2User oAuth2User = new DefaultOAuth2UserService().loadUser(userRequest);
            String email = oAuth2User.getAttribute("email");
            String name = oAuth2User.getAttribute("name");
            String picture = oAuth2User.getAttribute("picture");
            String locale = oAuth2User.getAttribute("locale");
            // Check if user exists, else save to DB
            Users user = usersRepo.findByEmail(email);
            if (user == null) {
                user = new Users(email, name, "oauth2"); // password dummy
                usersRepo.save(user);
            }

            return new DefaultOAuth2User(
                    oAuth2User.getAuthorities(),
                    oAuth2User.getAttributes(),
                    "email"
            );
        };
    }


}
