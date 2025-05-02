package com.backend.service;

import com.backend.model.Users;
import com.backend.repository.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class UsersController {
    @Autowired
    private UsersService usersService;
    @Autowired
    private UsersRepo usersRepo;
    @PostMapping("/signup")
    public ResponseEntity<Users> createUser(@RequestBody Users users){
        System.out.println("users");
        return new ResponseEntity<>(usersService.saveUser(users), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<Users> checkUser(@RequestBody Users user){
        System.out.println(user);
        return new ResponseEntity<>(usersService.checkUser(user), HttpStatus.ACCEPTED);
    }
    @GetMapping("/current-user")
    public Users getCurrentUser(OAuth2AuthenticationToken auth) {
        Map<String, Object> attributes = auth.getPrincipal().getAttributes();
        String email = (String) attributes.get("email");

        return usersRepo.findByEmail(email);
    }

}
