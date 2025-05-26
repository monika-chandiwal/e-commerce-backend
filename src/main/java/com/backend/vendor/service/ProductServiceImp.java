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

    /**
     * @param id
     * @param 
     * @return
     */

        @Override
        public void updateProduct(int id, Products newProductData) {
            Products existingProduct = productRepo.findById(id)
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            existingProduct.setName(newProductData.getName());
            existingProduct.setDescription(newProductData.getDescription());
            existingProduct.setPrice(newProductData.getPrice());
            existingProduct.setQuantity(newProductData.getQuantity());
            existingProduct.setType(newProductData.getType());
            existingProduct.setBrand(newProductData.getBrand());
            existingProduct.setImageUrl(newProductData.getImageUrl());
            existingProduct.setSizes(newProductData.getSizes());

            productRepo.save(existingProduct);
        }


}
