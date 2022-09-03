package ru.kazemirov.sellerservice.repos;

import org.springframework.data.repository.CrudRepository;
import ru.kazemirov.sellerservice.domain.Seller;

public interface SellerRepo extends CrudRepository<Seller, Integer> {
}
