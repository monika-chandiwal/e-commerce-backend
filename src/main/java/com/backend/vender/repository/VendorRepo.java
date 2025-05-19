package com.backend.vender.repository;

import com.backend.vender.model.Vender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendorRepo extends JpaRepository<Vender,Integer> {

}
