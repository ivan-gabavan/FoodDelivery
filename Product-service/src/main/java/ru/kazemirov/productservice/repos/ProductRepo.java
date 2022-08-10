package ru.kazemirov.productservice.repos;

import org.springframework.data.repository.CrudRepository;
import ru.kazemirov.productservice.domain.Product;

public interface ProductRepo extends CrudRepository<Product, Integer> {
    //Product findById (Integer id);
}
