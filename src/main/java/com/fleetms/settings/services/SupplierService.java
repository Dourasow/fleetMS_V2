package com.fleetms.settings.services;

import com.fleetms.settings.model.Supplier;
import com.fleetms.settings.repositories.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;

    //Get All the Countries Method
    public List<Supplier> findAll()
    {
        return supplierRepository.findAll();
    }

    public void save(Supplier supplier)
    {
        supplierRepository.save(supplier);
    }

    public void delete(Integer id)
    {
        supplierRepository.delete(supplierRepository.getById(id));
    }

    public Supplier findById(Integer id) {
        return supplierRepository.findById(id).orElse(null);
    }

}
