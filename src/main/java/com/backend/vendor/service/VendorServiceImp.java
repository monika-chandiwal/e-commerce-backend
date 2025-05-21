package com.backend.vendor.service;

import com.backend.vendor.model.Vendor;
import com.backend.vendor.repository.VendorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendorServiceImp implements VendorService{
    @Autowired
    private VendorRepo vendorRepo;
    @Override
    public Vendor saveVendor(Vendor vendor) {

        return vendorRepo.save(vendor);

    }

    /**
     * @return
     */
    @Override
    public List<Vendor> getAllVendors() {
        return List.of();
    }

    /**
     * @param vendor
     * @return
     */
    @Override
    public Vendor checkVendor(Vendor vendor) {
        return null;
    }

    @Override
    public void deleteAllVendor() {

    }
    @Override
    public boolean vendorExist(Vendor vendor) {
        return false;
    }
}
