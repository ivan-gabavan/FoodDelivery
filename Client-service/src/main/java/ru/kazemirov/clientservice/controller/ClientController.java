package ru.kazemirov.clientservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.kazemirov.clientservice.domain.Client;
import ru.kazemirov.clientservice.service.ClientService;

@RestController
public class ClientController {
    @Autowired
    ClientService clientService;

    @PostMapping("add/user")
    public void addClient(@RequestBody Client newClient){
        clientService.addClient(newClient);
    }

    @GetMapping("get/user")
    public Client getClient(@RequestParam("id") Integer id){
        return clientService.getClient(id);
    }
}
