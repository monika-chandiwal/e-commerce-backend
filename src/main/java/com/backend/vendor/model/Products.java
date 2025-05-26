package com.backend.vendor.model;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;
    private Double price;
    private Integer quantity;
    private String sizes;
    private String type;
    private String imageUrl;
    private  String brand;

    @ManyToOne
    @JoinColumn(name = "vendor_id")
    private Vendor vendor;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public List<String> getSizes() {
        if (sizes == null || sizes.isEmpty()) return Collections.emptyList();
        return List.of(sizes.split(","));
    }

    public void setSizes(List<String> sizes) {
        this.sizes = String.join(",", sizes);
    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    public Products() {
    }

    public Products(Integer id, String name, String description, Double price, Integer quantity, List<String> sizes, String type,String brand, String imageUrl, Vendor vendor) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.sizes = sizes.toString();
        this.type = type;
        this.brand=brand;
        this.imageUrl = imageUrl;
        this.vendor = vendor;
    }

    public Products(String name, String description, Double price, Integer quantity, List<String> sizes, String type, String imageUrl, Vendor vendor) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        System.out.println(sizes);
        this.sizes = String.join(",", sizes);
        this.type = type;
        this.imageUrl = imageUrl;
        this.vendor = vendor;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}

