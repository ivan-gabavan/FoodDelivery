package ru.kazemirov.cartservice.repos;

import org.springframework.data.repository.CrudRepository;
import ru.kazemirov.cartservice.domain.ProductsInCart;

import java.util.List;

public interface CartRepo extends CrudRepository <ProductsInCart, Long> {
    ProductsInCart findByProductIdAndUserId (Integer productId, Integer userId);
    List<ProductsInCart> findByUserId (Integer userId);
}
