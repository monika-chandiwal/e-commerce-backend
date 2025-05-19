package com.backend.customer.service;

import com.backend.customer.model.ContactUs;
import com.backend.customer.repository.ContactRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class ContactServiceImp implements ContactService{
    /**
     * @param contactUs
     * @return
     */

    @Autowired
    private ContactRepo contactRepo;

    @Override
    public ContactUs saveQuery(ContactUs contactUs) {
        try {

            System.out.println("Received user: " + contactUs);
            return contactRepo.save(contactUs);
        } catch (DataIntegrityViolationException e) {
            // Handle the duplicate entry (unique constraint violation)
            throw new RuntimeException("Email already exists: " + contactUs.getEmail());
        }
    }
}
