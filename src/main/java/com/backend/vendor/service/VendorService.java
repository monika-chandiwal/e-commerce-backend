package com.backend.vendor.service;
import com.backend.vendor.model.Vendor;

import java.util.List;

public interface VendorService {
    public Vendor saveVendor(Vendor vendor);
    public List<Vendor> getAllVendors();
    public Vendor checkVendor(Vendor vendor);
    public void  deleteAllVendor();
    public  boolean vendorExist(Vendor vendor);

}
