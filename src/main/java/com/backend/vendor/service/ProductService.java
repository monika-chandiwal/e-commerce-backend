package com.backend.vendor.service;

import com.backend.vendor.model.Products;

import java.util.List;

public interface ProductService {
    public Products addProduct(Products products);
    public List<Products> getAll(int id);
    public void deleteProduct(int productId);
    public void deleteAllProduct();
    public void updateProduct(int id,Products products);
}
