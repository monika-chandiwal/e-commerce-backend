package com.backend.vendor.controller;

import com.backend.vendor.model.Products;
import com.backend.vendor.model.Vendor;
import com.backend.vendor.repository.ProductRepo;
import com.backend.vendor.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
                //System.out.println(products.getSizes().toString());
                System.out.println(newProduct.getSizes());
                return ResponseEntity.status(HttpStatus.CREATED).body(newProduct);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An unexpected error occurred");
        }
    }

    @GetMapping("/dashboard/{id}")
    public ResponseEntity<?> getProducts(@PathVariable Integer id) {
        List<Products> products = productRepo.findByVendorId(id);
        System.out.println(products);
        return products.isEmpty()
                ? ResponseEntity.status(HttpStatus.NOT_FOUND).body("No products found")
                : ResponseEntity.ok(products);
    }

    @DeleteMapping("/dashboard/deleteProduct/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable Integer productId){
        try {
            productService.deleteProduct(productId);
            return ResponseEntity.ok().body(" product Successfully deleted");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/dashboard/deleteAllProduct")
    public ResponseEntity<?> deleteAllProduct(){
        try {
            productService.deleteAllProduct();
            return ResponseEntity.ok().body(" products Successfully deleted");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping("/dashboard/productUpdate/{productId}")
    public ResponseEntity<?> updateProduct(@RequestBody Products products, @PathVariable ("productId")  int productId){
        try {
            productService.updateProduct(productId, products);
            return ResponseEntity.ok().body(products);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @GetMapping("/showAllProductsToUsers")
    public ResponseEntity<?> showAll(){
        try{
           List<Products>all= productService.getAllProducts();
            return all.isEmpty()
                    ? ResponseEntity.status(HttpStatus.NOT_FOUND).body("No products found")
                    : ResponseEntity.ok(all);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
