package com.fleetms.settings.services;

import com.fleetms.settings.model.Module;
import com.fleetms.settings.repositories.ModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModuleService {

    @Autowired
    private ModuleRepository moduleRepository;

    //Get All the Module Method
    public List<Module> getAll()
    {
       return moduleRepository.findAll();
    }

    public void save(Module module)
    {
        moduleRepository.save(module);
    }

    public void delete(Integer id)
    {
        moduleRepository.delete(moduleRepository.getById(id));
    }

    public Module getBYId(Integer id) {
        return moduleRepository.findById(id).orElse(null);
    }
}
