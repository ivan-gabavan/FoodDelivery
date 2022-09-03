package ru.kazemirov.courierservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kazemirov.courierservice.domain.Courier;
import ru.kazemirov.courierservice.repos.CourierRepo;
import ru.kazemirov.courierservice.repos.PassportRepo;

@Service
public class CourierService {
    @Autowired
    CourierRepo courierRepo;
    @Autowired
    PassportRepo passportRepo;

    public void addCourier(Courier newCourier){
        passportRepo.save(newCourier.getPassport());
        courierRepo.save(newCourier);

    }

    public Courier getCourier(Integer id){
        return courierRepo.findById(id).orElse(null);
    }


}