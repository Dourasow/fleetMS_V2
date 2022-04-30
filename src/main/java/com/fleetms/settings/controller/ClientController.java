package com.fleetms.settings.controller;

import com.fleetms.settings.model.Client;
import com.fleetms.settings.services.ClientService;
import com.fleetms.settings.services.CountryService;
import com.fleetms.settings.services.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ClientController {

    @Autowired
    private StateService stateService;
    @Autowired
    private CountryService countryService;
    @Autowired
    private ClientService clientService;

    public Model addModelAttributes(Model model)
    {
        model.addAttribute("states", stateService.findAll());
        model.addAttribute("countries", countryService.getAll());
        model.addAttribute("clients", clientService.findAll());
        return model;
    }

    //Get All Clients
    @GetMapping("/clients")
    public String findAll(Model model)
    {
        addModelAttributes(model);
        return "parameters/clients";
    }

    @GetMapping("/parameters/clientAdd")
    public String addClient(Model model)
    {
        model.addAttribute("countries", countryService.getAll());
        return "/parameters/clientAdd";
    }

    @GetMapping("/parameters/client/{op}/{id}")
    public String editClient(@PathVariable Integer id, @PathVariable String op, Model model)
    {
        addModelAttributes(model);
        model.addAttribute("client", clientService.findById(id));
        return "/parameters/client" +op;
    }

    //Add Client
    @PostMapping("/parameters/clients")
    public String addNew(Client client)
    {
        clientService.save(client);
        return "redirect:/clients";
    }

    @RequestMapping(value ="/parameters/clients/delete/{id}", method = {RequestMethod.GET, RequestMethod.DELETE})
    public String delete(@PathVariable Integer id) {
        clientService.delete(id);
        return "redirect:/clients";
    }
}
