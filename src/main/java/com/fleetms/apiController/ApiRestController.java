package com.fleetms.apiController;

import com.fleetms.settings.model.Country;
import com.fleetms.settings.services.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ApiRestController {

    @Autowired
    private CountryService countryService;

    @GetMapping("/api/parameters/countries")
    public List<Country> countries(Model model)
    {
        List<Country> countries = countryService.getAll();
        return countries;
    }
}
