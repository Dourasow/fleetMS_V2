package com.fleetms.settings.controller;


import com.fleetms.settings.model.Supplier;
import com.fleetms.settings.services.CountryService;
import com.fleetms.settings.services.StateService;
import com.fleetms.settings.services.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class SupplierController {

    @Autowired
    private StateService stateService;
    @Autowired
    private CountryService countryService;
    @Autowired
    private SupplierService supplierService;

    public Model addModelAttributes(Model model)
    {
        model.addAttribute("states", stateService.findAll());
        model.addAttribute("countries", countryService.getAll());
        model.addAttribute("suppliers", supplierService.findAll());
        return model;
    }

    //Get All Clients
    @GetMapping("/suppliers")
    public String findAll(Model model)
    {
        addModelAttributes(model);
        return "parameters/suppliers";
    }

    @GetMapping("/parameters/supplierAdd")
    public String addSupplier(Model model)
    {
        model.addAttribute("countries", countryService.getAll());
        return "/parameters/supplierAdd";
    }

    @GetMapping("/parameters/supplier/{op}/{id}")
    public String edit(@PathVariable Integer id, @PathVariable String op, Model model)
    {
        Supplier supplier = supplierService.findById(id);
        addModelAttributes(model);
        model.addAttribute("supplier", supplier);
        return "/parameters/supplier" +op;
    }

    //Add Suppliers
    @PostMapping("/parameters/suppliers")
    public String addNew(Supplier supplier)
    {
        supplierService.save(supplier);
        return "redirect:/suppliers";
    }

    @RequestMapping(value ="/parameters/suppliers/delete/{id}", method = {RequestMethod.GET, RequestMethod.DELETE})
    public String delete(@PathVariable Integer id) {
        supplierService.delete(id);
        return "redirect:/suppliers";
    }
}
