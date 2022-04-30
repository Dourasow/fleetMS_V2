package com.fleetms.settings.controller;

import com.fleetms.settings.model.Location;
import com.fleetms.settings.model.State;
import com.fleetms.settings.services.CountryService;
import com.fleetms.settings.services.LocationService;
import com.fleetms.settings.services.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LocationController {

    @Autowired
    private StateService stateService;
    @Autowired
    private CountryService countryService;
    @Autowired
    private LocationService locationService;

    public Model addModelAttributes(Model model)
    {
        model.addAttribute("states", stateService.findAll());
        model.addAttribute("countries", countryService.getAll());
        model.addAttribute("locations", locationService.findAll());
        return model;
    }

    //Get All States
    @GetMapping("/locations")
    public String findAll(Model model)
    {
        addModelAttributes(model);
        return "parameters/locations";
    }

    @GetMapping("/parameters/locationAdd")
    public String addLocation(Model model)
    {
        model.addAttribute("countries", countryService.getAll());
        return "/parameters/locationAdd";
    }

    @GetMapping("/parameters/location/{op}/{id}")
    public String editLocation(@PathVariable Integer id, @PathVariable String op, Model model)
    {
        addModelAttributes(model);
        model.addAttribute("location", locationService.findById(id));
        return "/parameters/location" +op;
    }

    //Add Location
    @PostMapping("/parameters/locations")
    public String addNew(Location location)
    {
        locationService.save(location);
        return "redirect:/locations";
    }

    @RequestMapping(value ="/parameters/locations/delete/{id}", method = {RequestMethod.GET, RequestMethod.DELETE})
    public String delete(@PathVariable Integer id) {
        locationService.delete(id);
        return "redirect:/locations";
    }
}
