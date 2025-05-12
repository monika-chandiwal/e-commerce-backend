package com.backend.service;

import com.backend.model.ContactUs;
import com.backend.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
@RestController
public class ContactController {

    @Autowired
            private ContactService contactService;

        @PostMapping("/contactUs")
        public ResponseEntity<?> saveContactinfo(@RequestBody ContactUs contactUs){
            System.out.println("inside -- "+contactUs);
            try {

                ContactUs newQuery= contactService.saveQuery(contactUs);
                return ResponseEntity.status(HttpStatus.CREATED).body(newQuery);
            } catch (Exception e) {
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("An unexpected error occurred");
            }
        }

}
