package ru.kazemirov.cartservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.kazemirov.cartservice.domain.ProductsInCart;
import ru.kazemirov.cartservice.domain.ProductsInCartWithCost;
import ru.kazemirov.cartservice.repos.CartRepo;

import java.util.ArrayList;

@Service
public class CartServise {

    @Autowired
    private CartRepo cartRepo;
    @Value("${products.costs.upl}")
    private String productsCostsUrl;

    private ArrayList<ProductsInCart> addCostToProductsList (ArrayList<ProductsInCart> products){
        ArrayList<ProductsInCart> productsWithCost = new ArrayList<>();
        for (var product: products){
            ResponseEntity<Double> responseEntity = new RestTemplate().getForEntity(
                    productsCostsUrl + "/get/cost?id=" + product.getProductId(),
                    Double.class);
            ProductsInCartWithCost productWithCost = new ProductsInCartWithCost(
                    product.getProductId(),
                    product.getCount(),
                    product.getUserId(),
                    responseEntity.getBody());
            productsWithCost.add(productWithCost);
        }
        return productsWithCost;
    }
    public ArrayList<ProductsInCart> getAllProducts(boolean withCost){
        ArrayList<ProductsInCart> products = new ArrayList<>();
        for (ProductsInCart productsInCart: cartRepo.findAll()){
            products.add(productsInCart);
        }
        if (!withCost)
            return products;
        return addCostToProductsList(products);
    }
    public ArrayList<ProductsInCart> getAllProductsUser(Integer userId, boolean withCost){
        ArrayList<ProductsInCart> products = new ArrayList<>();
        for (ProductsInCart productsInCart: cartRepo.findByUserId(userId)){
            products.add(productsInCart);
        }
        return products;
    }
    public void post (ProductsInCart productsInCart){
        cartRepo.save(productsInCart);
    }

    public void incCount( Integer productId, Integer userId ){
        ProductsInCart productsInCart = cartRepo.findByProductIdAndUserId(productId, userId);
        productsInCart.setCount(productsInCart.getCount() + 1);
    }
    public void decCount( Integer productId, Integer userId){
        ProductsInCart productsInCart = cartRepo.findByProductIdAndUserId(productId, userId);
        productsInCart.setCount(productsInCart.getCount() - 1);
    }
    public void setCount( Integer productId, Integer newCount, Integer userId ){
        ProductsInCart productsInCart = cartRepo.findByProductIdAndUserId(productId, userId);
        productsInCart.setCount( newCount );
    }
    public void deleteProduct (Integer productId, Integer userId) {
        ProductsInCart productsInCart = cartRepo.findByProductIdAndUserId(productId, userId);
        cartRepo.delete(productsInCart);
    }
}
