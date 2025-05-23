package com.backend.vendor.repository;

import com.backend.vendor.model.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepo extends JpaRepository<Products,Integer> {
       List<Products> findByVendorId(Integer vendor_id);

}
