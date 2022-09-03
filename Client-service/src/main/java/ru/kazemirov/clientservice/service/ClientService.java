package ru.kazemirov.clientservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kazemirov.clientservice.domain.Client;
import ru.kazemirov.clientservice.repos.ClientRepo;

@Service
public class ClientService {
    @Autowired
    ClientRepo clientRepo;

    public void addClient(Client newClient){
        clientRepo.save(newClient);
    }

    public Client getClient(Integer id){
        return clientRepo.findById(id).orElse(null);
    }


}
