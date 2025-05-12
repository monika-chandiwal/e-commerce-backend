package com.backend.repository;

import com.backend.model.ContactUs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Repository
public interface ContactRepo extends JpaRepository <ContactUs,Integer>{
}
