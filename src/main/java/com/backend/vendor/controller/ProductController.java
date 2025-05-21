package com.backend.vendor.controller;

import com.backend.vendor.model.Products;
import com.backend.vendor.model.Vendor;
import com.backend.vendor.repository.ProductRepo;
import com.backend.vendor.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
@RestController
@RequestMapping("/vendor")
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private ProductRepo productRepo;

    @PostMapping("/addProduct")
    public ResponseEntity <?> add(@RequestBody Products products){
        try {
                Products newProduct = productService.addProduct(products);
                System.out.println(newProduct);
                return ResponseEntity.status(HttpStatus.CREATED).body(newProduct);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An unexpected error occurred");
        }
    }

}
