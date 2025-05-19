package com.backend.vender.service;
import com.backend.vender.model.Vender;

import java.util.List;

public interface VendorService {
    public Vender saveVendor(Vender vender);
    public List<Vender> getAllVenders();
    public Vender checkVender(Vender vender);
    public void  deleteAllVender();
    public  boolean venderExist(Vender vender);

}
