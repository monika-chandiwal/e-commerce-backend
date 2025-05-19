package com.backend.vender.service;

import com.backend.vender.model.Vender;
import com.backend.vender.repository.VendorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendorServiceImp implements VendorService{
    @Autowired
    private VendorRepo vendorRepo;
    @Override
    public Vender saveVendor(Vender vender) {
        return null;
    }

    /**
     * @return
     */
    @Override
    public List<Vender> getAllVenders() {
        return List.of();
    }

    /**
     * @param vender
     * @return
     */
    @Override
    public Vender checkVender(Vender vender) {
        return null;
    }

    @Override
    public void deleteAllVender() {

    }
    @Override
    public boolean venderExist(Vender vender) {
        return false;
    }
}
