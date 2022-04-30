package com.fleetms.settings.services;

import com.fleetms.settings.model.Location;
import com.fleetms.settings.model.State;
import com.fleetms.settings.repositories.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationService {

    @Autowired
    private LocationRepository locationRepository;

    //Get All the Countries Method
    public List<Location> findAll()
    {
        return locationRepository.findAll();
    }

    public void save(Location location)
    {
        locationRepository.save(location);
    }

    public void delete(Integer id)
    {
        locationRepository.delete(locationRepository.getById(id));
    }

    public Location findById(Integer id) {
        return locationRepository.findById(id).orElse(null);
    }

}
