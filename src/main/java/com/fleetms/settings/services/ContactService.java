package com.fleetms.settings.services;

import com.fleetms.settings.model.Contact;
import com.fleetms.settings.model.Country;
import com.fleetms.settings.repositories.ContactRepository;
import com.fleetms.settings.repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    //Get All the Contact Method
    public List<Contact> getAll()
    {
        return contactRepository.findAll();
    }

    public void save(Contact contact)
    {
        contactRepository.save(contact);
    }

    public void delete(Integer id)
    {
        contactRepository.delete(contactRepository.getById(id));
    }

    public Contact getById(Integer id) {
        return contactRepository.findById(id).orElse(null);
    }
}
