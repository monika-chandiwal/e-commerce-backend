package com.backend.vendor.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.*;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String description;
    private Double price;
    private Integer quantity;

    @Lob
    @Column(name = "sizes", columnDefinition = "TEXT")
    private String sizesJson;

    @Transient
    private Map<String, String> sizes;

    private String type;
    private String imageUrl;
    private String brand;

    @ManyToOne
    @JoinColumn(name = "vendor_id")
    private Vendor vendor;

    // Getters and Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }

    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }

    public Map<String, String> getSizes() {
        if (sizes == null && sizesJson != null) {
            try {
                ObjectMapper mapper = new ObjectMapper();
                sizes = mapper.readValue(sizesJson, new TypeReference<Map<String, String>>() {});
            } catch (IOException e) {
                e.printStackTrace();
                sizes = new HashMap<>();
            }
        }
        return sizes != null ? sizes : Collections.emptyMap();
    }

    public void setSizes(Map<String, String> sizes) {
        this.sizes = sizes;
        try {
            ObjectMapper mapper = new ObjectMapper();
            this.sizesJson = mapper.writeValueAsString(sizes);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public Vendor getVendor() { return vendor; }
    public void setVendor(Vendor vendor) { this.vendor = vendor; }

    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }

    // Constructors
    public Products() {}

    public Products(Integer id, String name, String description, Double price, Integer quantity,
                    Map<String, String> sizes, String type, String brand, String imageUrl, Vendor vendor) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        setSizes(sizes);
        this.type = type;
        this.brand = brand;
        this.imageUrl = imageUrl;
        this.vendor = vendor;
    }

    public Products(String name, String description, Double price, Integer quantity,
                    Map<String, String> sizes, String type, String imageUrl, Vendor vendor) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        setSizes(sizes);
        this.type = type;
        this.imageUrl = imageUrl;
        this.vendor = vendor;
    }

    public Products(String name, String description, Double price, Integer quantity,
                    Map<String, String> sizes, String type, String imageUrl, String brand) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        setSizes(sizes);
        this.type = type;
        this.imageUrl = imageUrl;
        this.brand = brand;
    }
}
