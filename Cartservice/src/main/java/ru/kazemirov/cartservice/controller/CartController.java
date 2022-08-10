package ru.kazemirov.cartservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.kazemirov.cartservice.domain.ProductsInCart;
import ru.kazemirov.cartservice.service.CartServise;
import java.util.ArrayList;

@RestController
public class CartController {

    @Autowired
    CartServise cartServise;

    @GetMapping("/cart/get/products")
    public ArrayList<ProductsInCart> getProductsOfUser(
            @RequestParam (value = "user", required = false) Integer userId,
            @RequestParam (value = "with-cost", required = false, defaultValue = "false") boolean withCost){
        if (userId == null)
            return cartServise.getAllProducts(withCost);
        else
            return cartServise.getAllProductsUser(userId, withCost);
    }
    @PostMapping ("/cart/add")
    public void addProduct (@RequestBody ProductsInCart productsInCart){
        cartServise.post(productsInCart);
    }
    @DeleteMapping ("cart/remove/product")
    public void removeProduct (@RequestParam(value = "user") Integer userId, @RequestParam(value = "id") Integer productId){
        cartServise.deleteProduct(productId, userId);
    }
    @PutMapping ("cart/product-count/inc")
    public void productCountInc (@RequestParam(value = "user") Integer userId, @RequestParam(value = "id") Integer productId){
        cartServise.incCount(productId, userId);
    }
    @PutMapping ("cart/product-count/dec")
    public void productCountDec (@RequestParam(value = "user") Integer userId, @RequestParam(value = "id") Integer productId){
        cartServise.decCount(productId, userId);
    }
    @PutMapping ("cart/product-count/set")
    public void productCountSet (
            @RequestParam(value = "user") Integer userId,
            @RequestParam(value = "id") Integer productId,
            @RequestParam(value = "count") Integer count) {
        cartServise.setCount(productId, count, userId);
    }


}