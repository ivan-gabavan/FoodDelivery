package ru.kazemirov.productservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.kazemirov.productservice.domain.Product;
import ru.kazemirov.productservice.service.ProductService;

@RestController
public class ProductController {
    @Autowired
    ProductService productService;

    @PostMapping("/add-product")
    private Integer addProduct (@RequestBody Product product) {
        return productService.post(product);
    }
    @GetMapping("/get/cost")
    private Double getCost (@RequestParam(value = "id") Integer productId){
        return productService.getCost(productId);
    }
}
