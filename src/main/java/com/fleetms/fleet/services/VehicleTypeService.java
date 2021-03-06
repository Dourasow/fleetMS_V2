package com.fleetms.fleet.services;

import com.fleetms.fleet.model.VehicleType;
import com.fleetms.fleet.repositories.VehicleTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleTypeService {

    @Autowired
    private VehicleTypeRepository vehicleTypeRepository;


    public List<VehicleType> findAll() {
        return vehicleTypeRepository.findAll();
    }

    public Optional<VehicleType> findById(Integer id) {
        return vehicleTypeRepository.findById(id);
    }

    public void save(VehicleType vehicleType) {
        vehicleTypeRepository.save(vehicleType);
    }

    public void delete(Integer id) {
        vehicleTypeRepository.deleteById(id);
    }
}
