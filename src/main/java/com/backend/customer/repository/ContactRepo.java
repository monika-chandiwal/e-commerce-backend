package com.backend.customer.repository;

import com.backend.customer.model.ContactUs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepo extends JpaRepository <ContactUs,Integer>{
}
