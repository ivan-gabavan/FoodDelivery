package ru.kazemirov.gateway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import ru.kazemirov.gateway.service.GatewayService;

@RestController
public class GatewayController {
    @Autowired
    GatewayService gatewayService;

    @PostMapping("/user/product/add")
    public void addProduct(HttpEntity<String> httpEntity){
        gatewayService.postProductInCart(httpEntity);
    }

}
