package com.backend.service;

import com.backend.model.Users;
import com.backend.repository.UsersRepo;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

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
    public ResponseEntity<?> checkUser(@RequestBody Users user) {
        System.out.println("Login attempt for: " + user.getEmail());

        Users authenticatedUser = usersService.checkUser(user);
        System.out.println("Authenticated user: " + authenticatedUser);

        if (authenticatedUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }

        return ResponseEntity.ok(authenticatedUser);
    }


    // Get the currently authenticated user (for OAuth2)
    @GetMapping("/current-user")
    public ResponseEntity<?> getCurrentUser(OAuth2AuthenticationToken auth) {
        try {
            if (auth == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Not authenticated");
            }

            Map<String, Object> attributes = auth.getPrincipal().getAttributes();
            String email = (String) attributes.get("email");
            System.out.println(email);
            Users user = usersRepo.findByEmail(email);
            if (user == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found in database");
            }

            return ResponseEntity.ok(user); // Successfully returned user
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error fetching user information");
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
    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.logout(); // Invalidate session
            return ResponseEntity.ok("Logged out");
        } catch (ServletException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Logout failed");
        }
    }

}
