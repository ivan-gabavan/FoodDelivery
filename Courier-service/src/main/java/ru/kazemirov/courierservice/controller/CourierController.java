package ru.kazemirov.courierservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.kazemirov.courierservice.domain.Courier;
import ru.kazemirov.courierservice.service.CourierService;

@RestController
public class CourierController {
    @Autowired
    CourierService courierService;

    @PostMapping("add/user")
    public void addCourier(@RequestBody Courier newCourier){
        courierService.addCourier(newCourier);
    }

    @GetMapping("get/user")
    public Courier getCourier(@RequestParam("id") Integer id){
        return courierService.getCourier(id);
    }
}
