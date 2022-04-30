package com.fleetms.settings.controller;

import com.fleetms.settings.model.Contact;
import com.fleetms.settings.model.Country;
import com.fleetms.settings.services.ContactService;
import com.fleetms.settings.services.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ContactController {

    @Autowired
    private ContactService contactService;

    //This Method inherit the getAll method from the service class
    @GetMapping("/contacts")
    public String getAll(Model model)
    {
        List<Contact> contacts =  contactService.getAll();
        model.addAttribute("contacts", contacts);
        return "parameters/contacts";
    }

    //The Get Country By Id
  /*  @GetMapping("/parameters/contact/{id}")
    @ResponseBody
    public Country getCountry(@PathVariable Integer id){
       return contactService.getById(id);
    }*/

    @GetMapping("/contactAdd")
    public String addContact()
    {
        return "/parameters/contactAdd";
    }

    @GetMapping("/contactEdit/{id}")
    public String editContact(@PathVariable Integer id, Model model)
    {
        Contact contact = contactService.getById(id);
        model.addAttribute("contact", contact);
        return "/parameters/contactEdit";
    }


    @GetMapping("/contactDetails/{id}")
    public String detailsContact(@PathVariable Integer id, Model model)
    {
        Contact contact = contactService.getById(id);
        model.addAttribute("contact", contact);
        return "/parameters/contactDetails";
    }
    @PostMapping("/contacts")
    public String save(Contact contact)
    {
        contactService.save(contact);
        return "redirect:/contacts";
    }

    @RequestMapping(value ="/contacts/delete/{id}", method = {RequestMethod.GET, RequestMethod.DELETE})
    public String delete(@PathVariable Integer id)
    {
        contactService.delete(id);
        return "redirect:/contacts";
    }

    @RequestMapping(value ="/contacts/update/{id}", method = {RequestMethod.GET, RequestMethod.PUT})
    public String update(Contact contact)
    {
        contactService.save(contact);
        return "redirect:/contacts";
    }
}
