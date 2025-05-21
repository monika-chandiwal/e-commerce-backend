package com.backend.vendor.repository;

import com.backend.vendor.model.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendorRepo extends JpaRepository<Vendor,Integer> {
      Vendor findByPhoneNumber(String phoneNumber);
      Vendor findByEmailAndPassword(String email,String password);
}
