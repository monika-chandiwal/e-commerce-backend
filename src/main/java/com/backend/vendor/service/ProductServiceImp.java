package com.backend.vendor.service;

import com.backend.vendor.model.Products;
import com.backend.vendor.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImp implements ProductService{
    @Autowired
    private ProductRepo productRepo;

    @Override
    public Products addProduct(Products products) {
        return productRepo.save(products);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public List<Products> getAll(int id) {
        return List.of();
    }

    /**
     * @param productId
     * @return
     */
    @Override
    public void deleteProduct(int productId) {
        productRepo.deleteById(productId);
    }

    /**
     *
     */
    @Override
    public void deleteAllProduct() {
           productRepo.deleteAll();
    }
}
