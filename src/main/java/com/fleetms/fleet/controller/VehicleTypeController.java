package com.fleetms.fleet.controller;

import com.fleetms.fleet.model.VehicleType;
import com.fleetms.fleet.services.VehicleTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class VehicleTypeController {

    @Autowired
    private VehicleTypeService vehicleTypeService;

    //Get All Vehicles Types
    @GetMapping("/fleet/vehicleTypes")
    public String findAll(Model model)
    {
        model.addAttribute("vehicleTypes", vehicleTypeService.findAll());
        return "/fleet/vehicleTypes";
    }

    @RequestMapping("/fleet/vehicleType/{id}")
    @ResponseBody
    public Optional<VehicleType> findById(@PathVariable Integer id){
        return vehicleTypeService.findById(id);
    }

    //Add Vehicle
    @PostMapping(value = "/fleet/vehicleTypes")
    public String addNew(VehicleType vehicleType)
    {
        vehicleTypeService.save(vehicleType);
        return "redirect:/fleet/vehicleTypes";
    }

    @RequestMapping(value= "/fleet/vehicleType/delete/{id}", method = {RequestMethod.GET, RequestMethod.DELETE})
    public String delete(@PathVariable Integer id)
    {
        vehicleTypeService.delete(id);
        return "redirect:/fleet/vehicleTypes";
    }
}
