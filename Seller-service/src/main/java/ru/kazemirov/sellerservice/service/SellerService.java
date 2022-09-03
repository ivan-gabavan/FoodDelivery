package ru.kazemirov.sellerservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kazemirov.sellerservice.domain.Seller;
import ru.kazemirov.sellerservice.repos.SellerRepo;

@Service
public class SellerService {
    @Autowired
    SellerRepo sellerRepo;

    public void addSeller(Seller newSeller){
        sellerRepo.save(newSeller);
    }

    public Seller getSeller(Integer id){
        return sellerRepo.findById(id).orElse(null);
    }
}
