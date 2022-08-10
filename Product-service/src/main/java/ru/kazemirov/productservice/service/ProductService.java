package ru.kazemirov.productservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kazemirov.productservice.domain.Product;
import ru.kazemirov.productservice.repos.ProductRepo;

@Service
public class ProductService {
    @Autowired
    private ProductRepo productRepo;

    public Integer post (Product product){
        return productRepo.save(product).getId();
    }

    public Double getCost (Integer productId){
        Product product = productRepo.findById(productId).orElse(null);
        return product.getCost() * (100d - product.getDiscount())/100d;
    }

}
