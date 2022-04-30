package com.fleetms.settings.services;

import com.fleetms.settings.model.Client;
import com.fleetms.settings.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    //Get All the Countries Method
    public List<Client> findAll()
    {
        return clientRepository.findAll();
    }

    public void save(Client client)
    {
        clientRepository.save(client);
    }

    public void delete(Integer id)
    {
        clientRepository.delete(clientRepository.getById(id));
    }

    public Client findById(Integer id) {
        return clientRepository.findById(id).orElse(null);
    }

}
