package ru.kazemirov.sellerservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.kazemirov.sellerservice.domain.Seller;
import ru.kazemirov.sellerservice.service.SellerService;

@RestController
public class SellerController {
    @Autowired
    SellerService sellerService;

    @PostMapping("add/user")
    void addSeller(@RequestBody Seller seller){
        sellerService.addSeller(seller);
    }

    @GetMapping("get/user")
    Seller getSeller(@RequestParam(value = "id") Integer id){
        return sellerService.getSeller(id);
    }
}
