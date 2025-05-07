package com.backend.service;

import com.backend.model.Users;
import com.backend.repository.UsersRepo;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;
import java.util.HashMap;

@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
@RestController
public class UsersController {

    @Autowired
    private UsersService usersService;
    @Autowired
    private UsersRepo usersRepo;

    // Sign-up new user
    @PostMapping("/signup")
    public ResponseEntity<Users> createUser(@RequestBody Users users) {
        try {
            Users newUser = usersService.saveUser(users);
            return new ResponseEntity<>(newUser, HttpStatus.CREATED);
        } catch (Exception e) {
            // Log error and return internal server error if there's an issue
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // Login with credentials
    @PostMapping("/login")
    public ResponseEntity<?> checkUser(@RequestBody Users user, HttpSession session) {
        System.out.println("Login attempt for: " + user.getEmail());

        Users authenticatedUser = usersService.checkUser(user);
        System.out.println("Authenticated user: " + authenticatedUser);

        if (authenticatedUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
        session.setAttribute("username", user.getUsername());
        return ResponseEntity.ok(authenticatedUser);
    }


    // Get the currently authenticated user (for OAuth2)
    private static final Logger logger = LoggerFactory.getLogger(UsersController.class);

    // Get the currently authenticated user (for OAuth2)
    @GetMapping("/current-user")
    public ResponseEntity<?> getCurrentUser() {
        try {
            var auth = SecurityContextHolder.getContext().getAuthentication();

            if (auth == null || !auth.isAuthenticated()) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Not authenticated");
            }

            if (!(auth.getPrincipal() instanceof OAuth2User oAuth2User)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not an OAuth2 user");
            }

            Map<String, Object> attributes = oAuth2User.getAttributes();
            logger.info("Authenticated user: {}", attributes);

            // Use HashMap to allow null values
            Map<String, Object> userData = new HashMap<>();
            userData.put("name", attributes.get("name"));
            userData.put("email", attributes.get("email"));
            userData.put("picture", attributes.get("picture"));
            userData.put("locale", attributes.get("locale"));

            logger.info("User data: {}", userData);

            return ResponseEntity.ok(userData);

        } catch (Exception e) {
            logger.error("Error fetching user info: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error fetching user info");
        }
    }



    // OAuth2 login success, redirect to frontend
    @GetMapping("/oauth2/success")
    public void redirectToFrontend(HttpServletResponse response) throws IOException {
        try {
            response.sendRedirect("http://localhost:5173/home");
        } catch (IOException e) {
            e.printStackTrace();
            throw new IOException("Error during redirect");
        }
    }

    // Delete all users (for admin use, be cautious with this endpoint)
    @DeleteMapping("/delete-all-users")
    public ResponseEntity<String> deleteAllUsers() {
        try {
            usersService.deleteAllUsers();
            return ResponseEntity.ok("All users deleted");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting users");
        }
    }
    @GetMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {
        try {
            request.logout();
            request.getSession().invalidate();
            return ResponseEntity.ok("Logged out successfully");
        } catch (ServletException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Logout failed");
        }
    }



}
